package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.dto.PagosDTO;
import com.ingsw.petpal.exception.DuplicatePaymentException;
import com.ingsw.petpal.exception.PaymentProcessingException;
import com.ingsw.petpal.exception.ResourceNotFoundException;
import com.ingsw.petpal.model.entity.enums.PaymentStatus;
import com.ingsw.petpal.repository.ContratsRepository;

import com.ingsw.petpal.mapper.PagosMapper;
import com.ingsw.petpal.model.entity.Contrats;
import com.ingsw.petpal.model.entity.Pagos;
import com.ingsw.petpal.repository.PagosRepository;
import com.ingsw.petpal.service.PagosService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PagosServiceImpl implements PagosService {

    private final PagosRepository pagosRepository;
    private final PagosMapper pagosMapper;

    private final ContratsRepository contratsRepository;

    @Transactional(readOnly = true)
    @Override
    public List<PagosDTO> getAll() {
        List<Pagos> pagosList = pagosRepository.findAll();
        return pagosList.stream().map(pagosMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public PagosDTO findById(Integer id) {
        Pagos pagos = pagosRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado"));
        return pagosMapper.toDTO(pagos);
    }

    @Transactional
    @Override
    public PagosDTO create(PagosDTO pagosDTO) {
        // Verificar si ya existe un pago con el mismo método y estado
        pagosRepository.findByMetodoPagoAndEstadoPago(pagosDTO.getMetodoPago(), pagosDTO.getEstadoPago())
                .ifPresent(pago -> {
                    throw new DuplicatePaymentException("Ya existe un pago con el mismo método y estado");
                });


        // Verificar si el contrato existe
        Contrats contrato = contratsRepository.findById(pagosDTO.getContratacionIdd())
                .orElseThrow(() -> new ResourceNotFoundException("Contrato no encontrado con id: " + pagosDTO.getContratacionIdd()));

        // Mapear DTO a entidad
        Pagos pagos = pagosMapper.toEntity(pagosDTO);
        pagos.setEstadoPago(PaymentStatus.PENDING);
        pagos.setContratacionIdd(contrato.getId());
        pagos.setValorPago(pagosDTO.getValorPago());
        pagos.setContrato(contrato);
        // Intentar guardar el pago
        try {
            pagos = pagosRepository.save(pagos);
        } catch (Exception e) {
            throw new PaymentProcessingException("Error al procesar el pago: " + e.getMessage());
        }

        return pagosMapper.toDTO(pagos);
    }

    @Transactional
    @Override
    public PagosDTO update(Integer id, PagosDTO updatedPagosDTO) {
        Pagos pagosFromDb = pagosRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado"));

        pagosFromDb.setMetodoPago(updatedPagosDTO.getMetodoPago());
        //pagosFromDb.setEstadoPago(updatedPagosDTO.getEstadoPago());
        pagosFromDb.setEstadoPago(PaymentStatus.valueOf(updatedPagosDTO.getEstadoPago()));
        pagosFromDb.setFechaPago(updatedPagosDTO.getFechaPago());

        pagosFromDb = pagosRepository.save(pagosFromDb);
        return pagosMapper.toDTO(pagosFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Pagos pagosFromDb = pagosRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado"));
        pagosRepository.delete(pagosFromDb);
    }

    @Override
    public PagosDTO confirmPurchase(Integer pagosId) {

        // Obtener la entidad Invoice directamente desde el repositorio
        Pagos invoice = pagosRepository.findById(pagosId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found"));

        // Confirmar la compra: cambio de estado
        invoice.setEstadoPago(PaymentStatus.COMPLETED);

        // Guardar y retornar el DTO actualizado
        Pagos updatedInvoice = pagosRepository.save(invoice);
        return pagosMapper.toDTO(updatedInvoice);
    }
}
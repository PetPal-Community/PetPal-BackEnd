package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.dto.PagosDTO;
import com.ingsw.petpal.dto.PagosDetails;
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

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PagosServiceImpl implements PagosService {

    private final PagosRepository pagosRepository;
    private final PagosMapper pagosMapper;

    private final ContratsRepository contratsRepository;

    @Transactional(readOnly = true)
    @Override
    public List<PagosDetails> getAll() {
        List<Pagos> pagosList = pagosRepository.findAll();
        return pagosList.stream()
                .map(pagosMapper::toDetailsDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public PagosDetails findById(Integer id) {
        Pagos pagos = pagosRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado"));
        return pagosMapper.toDetailsDTO(pagos);
    }

    @Transactional
    @Override
    public PagosDetails create(PagosDTO pagosDTO) {
        /*// Verificar si ya existe un pago con el mismo método y estado
        pagosRepository.findByMetodoPagoAndEstadoPago(pagosDTO.getMetodoPago(), pagosDTO.getEstadoPago())
                .ifPresent(pago -> {
                    throw new DuplicatePaymentException("Ya existe un pago con el mismo método y estado");
                });
           */

        // Verificar si el contrato existe
        Contrats contrato = contratsRepository.findById(pagosDTO.getContratacionIdd())
                .orElseThrow(() -> new ResourceNotFoundException("Contrato no encontrado con id: " + pagosDTO.getContratacionIdd()));

        // Mapear DTO a entidad
        Pagos pagos = pagosMapper.toEntity(pagosDTO);
        pagos.setEstadoPago(PaymentStatus.PENDING);
        pagos.setContrato(contrato);
        pagos.setValorPago(pagosDTO.getValorPago());
        pagos.setContrato(contrato);
        pagos.setFechaPagoCreado(LocalDateTime.now());
        // Intentar guardar el pago
        try {
            pagos = pagosRepository.save(pagos);
        } catch (Exception e) {
            throw new PaymentProcessingException("Error al procesar el pago: " + e.getMessage());
        }

        return pagosMapper.toDetailsDTO(pagosRepository.save(pagos));
    }

    @Transactional
    @Override
    public PagosDetails update(Integer id, PagosDTO updatedPagosDTO) {
        Pagos pagosFromDb = pagosRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado"));

        pagosFromDb.setMetodoPago(updatedPagosDTO.getMetodoPago());
        //pagosFromDb.setEstadoPago(updatedPagosDTO.getEstadoPago());
        //pagosFromDb.setEstadoPago(PaymentStatus.valueOf(updatedPagosDTO.getEstadoPago()));
        pagosFromDb.setValorPago(updatedPagosDTO.getValorPago());
        pagosFromDb.setFechaPagoCreado(LocalDateTime.now());
        pagosFromDb = pagosRepository.save(pagosFromDb);
        return pagosMapper.toDetailsDTO(pagosRepository.save(pagosFromDb));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Pagos pagosFromDb = pagosRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado"));
        pagosRepository.delete(pagosFromDb);
    }

    @Override
    public PagosDetails confirmPurchase(Integer pagosId) {

        // Obtener la entidad Invoice directamente desde el repositorio
        Pagos invoice = pagosRepository.findById(pagosId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found"));

        // Confirmar la compra: cambio de estado
        invoice.setEstadoPago(PaymentStatus.COMPLETED);
        invoice.setFechaPagoPagado(LocalDateTime.now());

        // Guardar y retornar el DTO actualizado
        Pagos updatedInvoice = pagosRepository.save(invoice);
        return pagosMapper.toDetailsDTO(updatedInvoice);
    }

    @Override
    public PagosDetails buscarPorContratoID(Integer contratoID){
        Contrats contrato = contratsRepository.findById(contratoID)
                .orElseThrow(() -> new ResourceNotFoundException("Contrato no encontrado con id: " + contratoID));

        Pagos pagoHallado = pagosRepository.findByContrato(contrato);
        return pagosMapper.toDetailsDTO(pagoHallado);
    }
}
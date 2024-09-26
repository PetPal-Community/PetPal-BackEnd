package com.ingsw.Petpal.service.impl;

import com.ingsw.Petpal.dto.PagosDTO;
import com.ingsw.Petpal.exception.BadRequestException;
import com.ingsw.Petpal.exception.DuplicatePaymentException;
import com.ingsw.Petpal.exception.PaymentProcessingException;
import com.ingsw.Petpal.exception.ResourceNotFoundException;
import com.ingsw.Petpal.mapper.PagosMapper;
import com.ingsw.Petpal.model.entity.Pagos;
import com.ingsw.Petpal.repository.PagosRepository;
import com.ingsw.Petpal.service.PagosService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PagosServiceImpl implements PagosService {

    private final PagosRepository pagosRepository;
    private final PagosMapper pagosMapper;

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

        // Mapear DTO a entidad
        Pagos pagos = pagosMapper.toEntity(pagosDTO);

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
        pagosFromDb.setEstadoPago(updatedPagosDTO.getEstadoPago());
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
}

package com.ingsw.petpal.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PagosDetails {
    private Integer id;

    private String metodoPago;

    private String estadoPago;

    private LocalDateTime fechaPagoPagado;

    private LocalDateTime fechaPagoCreado;

    private BigDecimal valorServicio;

    private BigDecimal valorPago;

    private String nombreCuidador;

    private String nomreServicio;

    private String nombreUsuario;
}

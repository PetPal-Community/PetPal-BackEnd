package com.ingsw.petpal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ContratoDetailsDTO {

    private Integer id;

    private LocalDate fechaContratacion;

    private String estado;

    private String detalles;

    private String servicioTipoServicio;
    private BigDecimal servicioPrecio;

    private String usuarioNombreComplt;

    private String cuidadorNombreComplt;
}

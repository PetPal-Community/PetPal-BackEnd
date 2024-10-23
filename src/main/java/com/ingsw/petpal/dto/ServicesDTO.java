package com.ingsw.petpal.dto;

import jakarta.validation.constraints.Size;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ServicesDTO {

    @NotBlank
    @Size(min = 5, max = 50)
    private String tipo_servicio;

    @Size(max = 120)
    private String descripcion;

    @NotBlank
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0.")
    private BigDecimal precio;
}

package com.ingsw.petpal.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class ServicesDetailsDTO {

    private Integer id;
    private String picRuta;
    private String tipo_servicio;

    private String descripcion;

    private BigDecimal precio;

    private String cuidadorNombre;
}

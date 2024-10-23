package com.ingsw.petpal.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ReviewDetailsDTO {

    private Integer id;

    private float calificacion;

    private String comentario;

    private LocalDateTime fechaCreacion;

    private String usuarioNombre;

    private String cuidadorNombre;
}

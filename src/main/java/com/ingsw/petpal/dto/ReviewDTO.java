package com.ingsw.petpal.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewDTO {

    private Integer id;

    @DecimalMin(value = "0.0", message = "La calificación no puede ser menor a 0")
    @DecimalMax(value = "5.0", message = "La calificación no puede ser mayor a 5")
    private float calificacion;

    @Size(min = 5, max = 120, message = "El comentario debe ser de 5 a 120 caracteres")
    private String comentario;

    private LocalDateTime fechaCreacion;

    @NotNull(message = "Usuario es obligatorio")
    private Integer usuarioGId;

    @NotNull(message = "Cuidador es obligatorio")
    private Integer cuidadorGId;


}

package com.ingsw.petpal.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentsDTO {

    private Integer id;

    @NotBlank(message = "El contenido del comentario es obligatorio")
    private String contenido; // Cambia 'content' a 'contenido'
}

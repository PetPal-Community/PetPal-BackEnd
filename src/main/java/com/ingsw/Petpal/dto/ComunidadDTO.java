package com.ingsw.Petpal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ComunidadDTO {

    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre debe tener 100 caracteres o menos")
    private String nombre;

    @Size(max = 255, message = "La descripción debe tener 255 caracteres o menos")
    private String descripcion;
}

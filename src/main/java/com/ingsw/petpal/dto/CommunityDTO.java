package com.ingsw.petpal.dto;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CommunityDTO {

    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre debe tener 100 caracteres o menos")
    private String nombre;

    @Size(max = 255, message = "La descripción debe tener 255 caracteres o menos")
    private String descripcion;

    //
    @NotNull(message = "El ID del creador es obligatorio")
    private Integer creadorGId; // Añadir el ID del creador
}

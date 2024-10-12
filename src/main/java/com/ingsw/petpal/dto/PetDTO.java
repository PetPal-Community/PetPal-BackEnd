package com.ingsw.petpal.dto;

import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class PetDTO {

    private Integer id;

    @NotBlank(message = "La raza es obligatoria")
    @Size(max = 50, message = "La raza debe tener 50 caracteres o menos")
    private String raza;

    @NotNull(message = "La edad es obligatoria")
    private Integer edad;

    @NotBlank(message = "El género es obligatorio")
    @Size(max = 10, message = "El género debe tener 10 caracteres o menos")
    private String genero;

    @NotBlank(message = "La especie es obligatoria")
    @Size(max = 20, message = "La especie debe tener 20 caracteres o menos")
    private String especie;
}

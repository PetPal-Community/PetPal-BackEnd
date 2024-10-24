package com.ingsw.petpal.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PetCreateUpdateDTO {

    private Integer id; // Puede ser nulo al crear una nueva mascota

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre tiene como máximo 50 caracteres")
    private String nombre;

    @NotBlank(message = "La raza es obligatoria")
    @Size(max = 50, message = "La raza tiene como máximo 50 caracteres")
    private String raza;

    @Min(value = 0, message = "La edad debe ser al menos 0")
    @Max(value = 30, message = "La edad no puede ser mayor a 30") // Ajusta el rango según tu aplicación
    private int edad;

    @NotBlank(message = "El género es obligatorio")
    @Size(max = 10, message = "El género tiene como máximo 10 caracteres")
    private String genero;

    @NotBlank(message = "La especie es obligatoria")
    @Size(max = 20, message = "La especie tiene como máximo 20 caracteres")
    private String especie;

    @NotNull(message = "El usuario es obligatorio")
    private Integer UsuarioId; // Este campo representa el ID del usuario propietario
}

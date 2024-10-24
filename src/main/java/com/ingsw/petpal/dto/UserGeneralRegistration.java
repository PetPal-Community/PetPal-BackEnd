package com.ingsw.petpal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserGeneralRegistration {
    // Carer - User  : Attributes
    @NotBlank(message="El nombre es obligatorio")
    @Size(max=50, message="El nombre debe tener 50 caracteres o menos")
    private String nombre;


    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 3, max = 50, message = "apellido debe ser de 3 a 20 caracteres")
    private String apellido;

    @NotBlank
    @Pattern(regexp = "\\d+", message = "El teléfono debe contener solo números.")
    private String telefono;

    // User General
    @NotBlank(message = "email obligatorio")
    @Email(message = "El formato debe ser de email")
    private String email;

    @NotBlank(message = "contraseña obligatoria")
    @Size(min=8, max = 20, message = "La contraseña debe estar entre 8 y 20 caracteres")
    private String contrasena;
}

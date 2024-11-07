package com.ingsw.petpal.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UsuarioDTO {

    private Integer id;
    private String picRuta;
    @NotBlank(message="El nombre es obligatorio")
    @Size(max=50, message="El nombre debe tener 50 caracteres o menos")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 3, max = 20, message = "apellido debe ser de 3 a 20 caracteres")
    private String apellido;

    @NotBlank
    @Pattern(regexp = "\\d+", message = "El teléfono debe contener solo números.")
    private String telefono;




}

package com.ingsw.petpal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CarerDTO {

    private String picRuta;
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 20, message = "nombre debe ser de 3 a 20 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 3, max = 20, message = "apellido debe ser de 3 a 20 caracteres")
    private String apellido;


    @NotBlank
    @Pattern(regexp = "\\d+", message = "El teléfono debe contener solo números.")
    private String telefono;


}

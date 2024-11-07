package com.ingsw.petpal.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PublicacionCreateDTO {
    private Integer id;
    private String picRuta;
    @NotBlank(message = "Contenido es obligatorio")
    @Size( max=200, message = "Solo puede tener un maximo de 200 caracteres")
    private String contenido;

    private LocalDateTime fechaPublicacion;

    private LocalDateTime fechaActualización;

    @NotNull(message = "Usuario es obligatorio")
    private Integer usuarioGId;

    private Integer comunidad;
}

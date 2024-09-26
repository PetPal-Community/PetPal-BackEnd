package com.ingsw.petpal.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MensajeDetailsDTO {

    public Integer id;
    private String contenido;

    private LocalDateTime fechaEnvio;

    private String usuarioNombre;

    private String cuidadorNombre;
}

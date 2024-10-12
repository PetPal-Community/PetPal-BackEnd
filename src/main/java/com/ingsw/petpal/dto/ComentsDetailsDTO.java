package com.ingsw.petpal.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ComentsDetailsDTO {
    private Integer id;

    private String contenido;

    private LocalDateTime fechacreacion;

    private LocalDateTime fechaupdate;

    private String usuarioNombre;

    private String publicacionDeNombre;
}

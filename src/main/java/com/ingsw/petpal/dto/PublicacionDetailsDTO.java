package com.ingsw.petpal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PublicacionDetailsDTO {
    private Integer id;
    private String contenido;
    private String picRuta;
    private LocalDateTime fechaPublicacion;

    private LocalDateTime fechaActualización;

    private String NombreUsuario;

    private String TituloComunidad;
}

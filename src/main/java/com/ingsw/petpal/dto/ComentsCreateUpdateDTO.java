package com.ingsw.petpal.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ComentsCreateUpdateDTO {

    private Integer id;

    @NotBlank(message = "Comentario debe tener su contenido")
    @Size(max = 120,message = "La descripcion tiene como máximo 120 caracteres")
    private String contenido;

    private LocalDateTime fechacreacion;

    private LocalDateTime fechaupdate;

    @NotNull(message = "Usuario Debe existir")
    private Integer usuario;

    @NotNull(message = "publicacion Debe existir")
    private Integer publicacion;
}

package com.ingsw.petpal.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class likesPublicacionDTO {
    private Integer usuarioId;        // ID del usuario que da el like
    private Integer publicacionId;    // ID de la publicación
    private LocalDateTime fecha;       // Fecha del like
}

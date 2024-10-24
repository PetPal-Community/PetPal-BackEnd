package com.ingsw.petpal.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReminderDetailsDTO {
    private Integer id;
    private String tipoRecordatorio;
    private String descripcion;
    private LocalDateTime hora;
    private Integer mascotaId; // ID de la mascota asociada
}

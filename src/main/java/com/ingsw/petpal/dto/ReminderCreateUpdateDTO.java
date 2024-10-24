package com.ingsw.petpal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReminderCreateUpdateDTO {
    @NotBlank(message = "El tipo de recordatorio es obligatorio")
    @Size(max = 50, message = "El tipo de recordatorio no puede exceder los 50 caracteres")
    private String tipoRecordatorio;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 200, message = "La descripción no puede exceder los 200 caracteres")
    private String descripcion;

    @NotNull(message = "La hora es obligatoria")
    private LocalDateTime hora;

    @NotNull(message = "El ID de la mascota es obligatorio")
    private Integer mascotaId; // ID de la mascota asociada
}

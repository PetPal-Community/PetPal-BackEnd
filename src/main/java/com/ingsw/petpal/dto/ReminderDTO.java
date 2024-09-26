package com.ingsw.petpal.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReminderDTO {

    @NotBlank(message = "Tipo Recordatorio es obligatorio")
    @Size(max = 50, message = "Tipo Recordatorio debe tener como máximo 50 caracteres")
    private String tipoRecordatorio;

    @NotBlank(message = "Descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "La fecha debe existir")
    @Future(message = "La fecha debe ser en el futuro")
    private LocalDateTime hora;
}

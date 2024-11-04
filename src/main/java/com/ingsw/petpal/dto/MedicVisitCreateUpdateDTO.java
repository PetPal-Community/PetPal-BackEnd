package com.ingsw.petpal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicVisitCreateUpdateDTO {

    private Integer id;

    @NotBlank(message = "El diagnóstico es obligatorio")
    private String diagnostico;

    @NotNull(message = "La fecha de visita es obligatoria")
    private LocalDate fechaVisita;

    @NotNull(message = "La mascota es obligatoria")
    private Integer mascotaId; // Clave foránea para la mascota asociada
}

package com.ingsw.petpal.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicVisitDetailsDTO {
    private Integer id;
    private String diagnostico;
    private LocalDate fechaVisita;
    private Integer mascotaId; // Clave foránea para la mascota asociada
}

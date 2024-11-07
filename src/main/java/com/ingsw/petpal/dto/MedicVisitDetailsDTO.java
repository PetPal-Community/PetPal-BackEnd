package com.ingsw.petpal.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicVisitDetailsDTO {
    private Integer id;
    private String diagnostico;
    private LocalDate fechaVisita;
    private String  nombreMascota; // Clave foránea para la mascota asociada
}

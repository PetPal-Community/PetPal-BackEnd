package com.ingsw.petpal.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TreatmentDetailsDTO {
    private Integer id;

    private String descripcion;

    private String medicamento;

    private String dosis; //puede ser int idk

    private LocalDate fechaInico;

    private LocalDate fechaFin;

    private String visitamedica;
}

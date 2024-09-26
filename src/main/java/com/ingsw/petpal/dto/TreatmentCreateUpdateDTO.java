package com.ingsw.petpal.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;


@Data
public class TreatmentCreateUpdateDTO {

    private Integer id;

    @NotBlank(message = "Descripcion es obligatoria")
    @Size(max = 120, message = "La descripcion tiene como máximo 120 caracteres")
    private String descripcion;

    @NotBlank(message = "Medicamento es obligatorio")
    @Size(max = 50, message = "El medicamento tiene como máximo 50 caracteres")
    private String medicamento;

    @NotBlank(message = "Dosis es importante")
    @Size(max = 100, message = "La dosis tiene como máximo 100 caracteres")
    private String dosis;

    @NotNull(message = "Fecha inicio es obligatoria")
    private LocalDate fechaInicio;

    @NotNull(message = "Fecha fin es obligatoria")
    //@Future(message = "La fecha fin debe ser en el futuro") // Descomentar si es necesario
    private LocalDate fechaFin;

    @NotNull(message = "Visita médica es obligatoria")
    private Integer visitaMedica;
}

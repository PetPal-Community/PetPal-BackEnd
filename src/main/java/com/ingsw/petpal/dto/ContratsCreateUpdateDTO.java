package com.ingsw.petpal.dto;


import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ContratsCreateUpdateDTO {

    private Integer id;

    @NotBlank(message = "Fecha de contratación obligatoria")
    private String duracionContrato;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @Size(max = 120, message = "Los detalles no deben tener más de 120 caracteres")
    private String detalles;

    @NotNull(message = "Servicio es obligatorio")
    private Integer servicio;

    @NotNull(message = "usuario es obligatorio")
    private Integer usuario;

    @NotNull(message = "cuidador es obligatorio")
    private Integer cuidador;

}

package com.ingsw.petpal.dto;



import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicVisitDTO {

    @NotBlank(message = "Debes ingresar el diagnostico de la visita médica")
    @Max(120)
    private String diagnostico;

    private LocalDate fechaVisita;
}
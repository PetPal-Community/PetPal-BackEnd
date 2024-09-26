package com.ingsw.petpal.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class MedicDocumentsCreateUpdateDTO {
    private Integer id;

    @NotBlank(message = "Debe existir el tipo de Documento")
    @Size(max = 40, message = "El tipo de documento no debe tener más de 40 caracteres")
    private String tipoDocumento;

    @Size(max = 120, message = "La descripción no debe tener más de 120 caracteres")
    private String descripcion;


    @NotNull(message = "Visita médica es obligatoria")
    private Integer visitaMedica;


}

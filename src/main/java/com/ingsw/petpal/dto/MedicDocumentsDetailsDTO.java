package com.ingsw.petpal.dto;


import lombok.Data;

@Data
public class MedicDocumentsDetailsDTO {

    private Integer id;

    private String tipoDocumento;

    private String descripcion;

    private String visitaMedicaDescripcion;
    private String NombreMascota;
}

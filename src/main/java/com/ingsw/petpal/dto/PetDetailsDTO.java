package com.ingsw.petpal.dto;

import lombok.Data;

@Data
public class PetDetailsDTO {
    private Integer id;

    private String raza;

    private String nombre;

    private int edad;

    private String genero;

    private String especie;

    private String usuarioId; // Puedes considerar incluir el nombre o email del usuario, dependiendo de tus necesidades
}

package com.ingsw.petpal.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private Integer id;
    private String token;
    private String nombre;
    private String apellido;
    private String role;
}

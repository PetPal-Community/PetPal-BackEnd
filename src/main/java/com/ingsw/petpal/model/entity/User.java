package com.ingsw.petpal.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name = "usuarios")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String contrasenia;
}

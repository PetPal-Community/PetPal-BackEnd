package com.ingsw.petpal.model.entity;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "personas")
public class UserGeneral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String contrasena;

    @OneToOne(mappedBy= "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private User usuario;

    @OneToOne(mappedBy= "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private Carer carer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="rol_id", referencedColumnName = "id")
    private Role role;
}

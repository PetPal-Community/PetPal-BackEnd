package com.ingsw.Petpal.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "comunidades")
public class Comunidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String descripcion;

    // Relación con Usuario (creador de la comunidad)
    @ManyToOne
    @JoinColumn(name = "creador_id")
    private Usuario creador;

    // Relación con ComunidadUser
    //@OneToMany(mappedBy = "comunidad", cascade = CascadeType.ALL)
    //private List<ComunidadUser> usuarios;
}

package com.ingsw.petpal.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "comunidades")
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String descripcion;

    // USUARIO COMUNIDAD
    @ManyToOne
    @JoinColumn(name = "creador_id")
    private User creador;

    @JsonIgnore
    @OneToMany(mappedBy = "comunidad", cascade = CascadeType.ALL)
    private List<ComunidadUser> usuarios;
    //

}

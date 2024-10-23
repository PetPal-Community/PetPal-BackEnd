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

    private Integer creadorId;
    /*
    // USUARIO COMUNIDAD
    @ManyToOne
    @JoinColumn(name = "creador_id")
    private User creador;

    @JsonIgnore
    @OneToMany(mappedBy = "comunidad", cascade = CascadeType.ALL)
    private List<ComunidadUser> usuarios;
    //


     */
    // Relación comentada, debe estar activa si es la correcta
    @ManyToOne
    @JoinColumn(name="usuario_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_comunidad_usuario"))
    private User creador;

    @JsonIgnore
    @OneToMany(mappedBy = "comunidad", cascade = CascadeType.ALL)
    private List<ComunidadUser> usuarios;
}

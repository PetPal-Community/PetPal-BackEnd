package com.ingsw.petpal.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "publicaciones")
public class Publicaciones implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String contenido;

    private LocalDateTime fechaPublicacion;

    private LocalDateTime fechaActualización;

    // PUBLICACIONES CREADAS POR USUARIOS(NO NULLABLE) y COMUNIDADES(NULLABLE)
    @ManyToOne
    @JoinColumn(name = "usuario_id",nullable = false,referencedColumnName = "id",foreignKey = @ForeignKey(name = "fk_usuario_publicacion") )
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "comunidad_id",referencedColumnName = "id",foreignKey = @ForeignKey(name = "fk_usuario_publicacion") )
    private Community comunidad;
    //

    // LIKES
    @JsonIgnore
    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL)
    private List<likesPublicacion> likes;
    //

    // COMENTARIOS EXISTENTES
    @JsonIgnore
    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL)
    private List<coments> comentarios;
    //


}

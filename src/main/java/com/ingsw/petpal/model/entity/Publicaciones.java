package com.ingsw.petpal.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "publicaciones")
public class Publicaciones implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String contenido;
    private LocalDateTime fechaPublicacion;
    private LocalDateTime fechaActualización;

    @ManyToOne
    @JoinColumn(name = "usuario_id",referencedColumnName = "id",foreignKey = @ForeignKey(name = "fk_usuario_publicacion") )
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "comunidad_id",referencedColumnName = "id",foreignKey = @ForeignKey(name = "fk_usuario_publicacion") )
    private Community comunidad;
}

package com.ingsw.petpal.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comentarios")
public class coments implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String contenido;
    private LocalDateTime fechacreacion;
    private LocalDateTime fechaupdate;

    @ManyToOne
    @JoinColumn(name = "usuario_id",referencedColumnName = "id",foreignKey = @ForeignKey(name = "fk_usuario_publicacion") )
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "comunidad_id",referencedColumnName = "id",foreignKey = @ForeignKey(name = "fk_usuario_publicacion") )
    private Community comunidad;
}

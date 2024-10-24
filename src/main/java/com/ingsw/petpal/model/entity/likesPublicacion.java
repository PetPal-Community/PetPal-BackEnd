package com.ingsw.petpal.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "likes_publicacion")
@IdClass(likesPublicacionFK.class) // Indica que se utiliza una clave compuesta
public class likesPublicacion implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_like_user"))
    private User usuario;

    @Id
    @ManyToOne
    @JoinColumn(name = "publicacion_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_like_publicacion"))
    private Publicaciones publicacion;

    private LocalDateTime fecha;
}

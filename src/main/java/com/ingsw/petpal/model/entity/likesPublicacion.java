package com.ingsw.petpal.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

// EN PRUEBAS

@Data
@Entity
@Table(name="likesPublicaciones")
public class likesPublicacion implements Serializable {

    @EmbeddedId
    private likesPublicacionID id; // Clave primaria compuesta

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuarioId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_usuario_likes"))
    private User usuario; // Usuario que dio el "me gusta"

    @ManyToOne
    @MapsId("publicacionId")
    @JoinColumn(name = "publicacionId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_publicacion_likes"))
    private Publicaciones publicacion; // Publicación a la que se le dio el "me gusta"

    private LocalDateTime fecha;
}

package com.ingsw.petpal.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="likesPublicacion")
@IdClass(likesPublicacionFK.class)
public class likesPublicacion implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_user_likes"))
    private User usuario;

    @Id
    @ManyToOne
    @JoinColumn(name = "publicacion_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_publicacion_likes"))
    private Publicaciones publicacion;

    private LocalDateTime fecha;
}

package com.ingsw.petpal.model.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class likesPublicacionFK implements Serializable {

    @ManyToOne
    @JoinColumn(name="usuario_id", referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_like_user"))
    private User usuario;

    @ManyToOne
    @JoinColumn(name="publicacion_id", referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_like_publicacion"))
    private Publicaciones publicacion;
}

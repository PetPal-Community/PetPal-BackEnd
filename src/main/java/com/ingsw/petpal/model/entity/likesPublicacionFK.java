package com.ingsw.petpal.model.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

// EN PRUEBAS
@Embeddable
@EqualsAndHashCode
public class likesPublicacionFK implements Serializable {

    @ManyToOne
    @JoinColumn(name="usuarioId", referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_like_user"))
    private User usuario;

    @ManyToOne
    @JoinColumn(name="publicacionId", referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_like_publicacion"))
    private Publicaciones publicacion;
}

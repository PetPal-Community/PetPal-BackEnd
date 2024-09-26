package com.ingsw.Petpal.model.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable // Se integra en otra clase
@EqualsAndHashCode
public class ComunidadUserPK implements Serializable {

    @ManyToOne
    @JoinColumn(name="usuarioId", referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_usercomunidad_user"))
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="comunidadId", referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_usercomunidad_comunidad"))
    private Comunidad comunidad;

}
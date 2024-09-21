package com.ingsw.petpal.model.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

// EN PRUEBAS
@Embeddable // Se integra en otra clase
@EqualsAndHashCode
public class FollowersPK implements Serializable {

    @ManyToOne
    @JoinColumn(name="usuarioseguidorId", referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_follow_userSeguidor"))
    private User usuarioseguidor;

    @ManyToOne
    @JoinColumn(name="usuarioseguidoId", referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_follow_userSeguido"))
    private User usuarioseguido;

}

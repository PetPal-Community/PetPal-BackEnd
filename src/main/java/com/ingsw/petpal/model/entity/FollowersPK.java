package com.ingsw.petpal.model.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

// EN PRUEBAS
@Embeddable
public class FollowersPK implements Serializable {
    private Integer usuarioseguidorId;

    private Integer usuarioseguidoId;

}

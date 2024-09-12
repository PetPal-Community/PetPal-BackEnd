package com.ingsw.petpal.model.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

// EN PRUEBAS
@Embeddable
public class likesPublicacionID implements Serializable {

    private Integer usuarioId;

    private Integer publicacionId;
}

package com.ingsw.petpal.model.entity;

import jakarta.persistence.*;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

// EN PRUEBAS

@Data
@Entity
@Table(name="likesPublicaciones")
@IdClass(likesPublicacionFK.class)
public class likesPublicacion implements Serializable {

    @Id
    private Integer usuario;

    @Id
    private Integer publicacion;

    private LocalDateTime fecha;
}

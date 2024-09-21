package com.ingsw.petpal.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comunidadUsuario")
@IdClass(ComunidadUserPK.class)
public class ComunidadUser {
    @Id
    private Integer usuario;

    @Id
    private Integer comunidad;

    @Column(name = "fechaQueSeUnio", nullable = false)
    private LocalDateTime FechaDeUnion;
}

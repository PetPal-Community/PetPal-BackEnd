package com.ingsw.petpal.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data // EN PRUEBAS
@Entity
@Table(name = "seguidores")
@IdClass(FollowersPK.class)
public class Followers {

    @Id
    private Integer usuarioseguidor;

    @Id
    private Integer usuarioseguido;

    @Column(name = "fechaEmpezoSeguir", nullable = false)
    private LocalDateTime fechaCuandoEmpezo;

}

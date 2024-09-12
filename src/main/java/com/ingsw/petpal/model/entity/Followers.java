package com.ingsw.petpal.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;


@Data // EN PRUEBAS
@Entity
@Table(name = "Seguidores")
public class Followers implements Serializable {

    @EmbeddedId
    private FollowersPK id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_usuario_siguiendo"))
    private User usuario; // Usuario que sigue

    @ManyToOne
    @MapsId("seguidorId")
    @JoinColumn(name = "seguidor_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_user_seguidor"))
    private User seguidor; // Usuario que es seguido

    private LocalDate fechaCuandoEmpezo;

}

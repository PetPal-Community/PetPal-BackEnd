package com.ingsw.petpal.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "likes_publicacion")
@IdClass(likesPublicacionFK.class) // Indica que se utiliza una clave compuesta
public class likesPublicacion implements Serializable {

    @Id
    private Integer usuario;

    @Id
    private Integer publicacion;

    private LocalDateTime fecha;
}

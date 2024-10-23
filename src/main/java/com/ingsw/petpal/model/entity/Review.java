package com.ingsw.petpal.model.entity;

import jakarta.persistence.*;

import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="reseñas")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private float calificacion;

    private String comentario;

    private LocalDateTime fechaCreacion;

    //EDitar para que solo haya una reseña de un usuario por el perfil de un cuidador
    @ManyToOne
    @JoinColumn(name="usuario_id", referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_user_review"))
    private User usuario;

    @ManyToOne
    @JoinColumn(name="cuidador_id",referencedColumnName = "id",foreignKey = @ForeignKey(name = "fk_cuidador_review"))
    private Carer cuidador;

}

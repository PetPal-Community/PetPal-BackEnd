package com.ingsw.petpal.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="mensajes")
public class Mensajes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String contenido;
    private LocalDateTime fechaEnvio;
    private LocalDateTime fechaUpdate;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_mensaje_usuario"))
    private User usuario;


    @ManyToOne
    @JoinColumn(name = "cuidador_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_mensaje_cuidador"))
    private Carer cuidador;
}

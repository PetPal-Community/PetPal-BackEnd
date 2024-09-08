package com.ingsw.petpal.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data // Limpia los constructores
@Entity
@Table(name = "mascotas")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String raza;
    private int edad;
    private String genero;
    private String especie;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_user_pet"))
    private User usuario;

}

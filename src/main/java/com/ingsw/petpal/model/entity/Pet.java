package com.ingsw.petpal.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.Data;

import java.util.List;

@Data // Limpia los constructores
@Entity
@Table(name = "mascotas")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String raza;

    private String nombre;

    private int edad;

    private String genero;

    private String especie;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_user_pet"))
    private User usuario;

    // VISITAS MEDICAS
    @JsonIgnore
    @OneToMany(mappedBy="mascota", cascade = CascadeType.ALL)
    private List<MedicVisit> visitasMedicas;
    //

    // RECORDATORIOS
    @JsonIgnore
    @OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL)
    private List<Reminder> recordatorios;


}

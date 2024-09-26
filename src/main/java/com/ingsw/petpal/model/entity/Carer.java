package com.ingsw.petpal.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

@Data
@Entity
@Table(name="cuidadores")
public class Carer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String apellido;

    private String email;

    private String telefono;


    private String contrasenia;

    // RESEÑAS
    @JsonIgnore
    @OneToMany( mappedBy="cuidador", cascade=CascadeType.ALL)
    private List<Review> resenias;

    //MENSAJES
    @JsonIgnore
    @OneToMany(mappedBy = "cuidador", cascade=CascadeType.ALL)
    private List<Mensajes> mensajesCuidador;

    //CONTRATOS
    @JsonIgnore
    @OneToMany(mappedBy = "cuidador", cascade = CascadeType.ALL)
    private List<Contrats> contratosCuidadores;

    // SERVICIOS
    @JsonIgnore
    @OneToMany(mappedBy = "cuidador", cascade = CascadeType.ALL)
    private List<Services> servicios;
}

package com.ingsw.petpal.model.entity;


import jakarta.persistence.*;

import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="Tratamientos")
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descripcion;

    private String medicamento;

    private String dosis; //puede ser int idk

    private LocalDate fechaInico;

    private LocalDate fechaFin;

    @ManyToOne
    @JoinColumn(name="visitaMedico_id",referencedColumnName = "id",foreignKey = @ForeignKey(name = "fk_visit_trata"))
    private MedicVisit visitaMedica;
}

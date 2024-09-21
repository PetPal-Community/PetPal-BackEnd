package com.ingsw.petpal.model.entity;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table
public class MedicDocuments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tipoDocumento;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name="VisitaMedica_id",referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_visit_doc"))
    private MedicVisit visitamedica;
}

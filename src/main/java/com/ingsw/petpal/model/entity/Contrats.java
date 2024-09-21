package com.ingsw.petpal.model.entity;

import jakarta.persistence.*;

import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "contrataciones")
public class Contrats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate fechaContratacion;

    private String estado;

    private String detalles;

    @ManyToOne
    @JoinColumn(name = "servicio_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_servicio_contrato"))
    private Services servicio;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_contrato_usuario"))
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "cuidador_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_contrato_cuidador"))
    private Carer cuidador;
}

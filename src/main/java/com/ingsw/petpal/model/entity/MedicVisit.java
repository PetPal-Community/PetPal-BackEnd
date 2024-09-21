package com.ingsw.petpal.model.entity;

import jakarta.persistence.*;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name="VisitasMedicas")
public class MedicVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String diagnostico;

    private LocalDate fechaVisita;

    @ManyToOne
    @JoinColumn(name="pet_id",referencedColumnName = "id",foreignKey = @ForeignKey(name = "fk_pet_visit"))
    private Pet mascota;

    // tratamientos
    @OneToMany(mappedBy="visitaMedica", cascade = CascadeType.ALL)
    private List<Treatment> tratamientos;
    //

    // DOCUMENTOS MEDICOS
    @OneToMany(mappedBy = "visitamedica", cascade = CascadeType.ALL)
    private List<MedicDocuments> documentosMedicos;
}

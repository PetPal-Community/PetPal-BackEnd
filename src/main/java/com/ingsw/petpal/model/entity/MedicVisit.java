package com.ingsw.petpal.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="VisitasMedicas")
public class MedicVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String diagnostico;
    private LocalDateTime fechaVisita;

    @ManyToOne
    @JoinColumn(name="pet_id",referencedColumnName = "id",foreignKey = @ForeignKey(name = "fk_pet_visit"))
    private Pet mascota;
}

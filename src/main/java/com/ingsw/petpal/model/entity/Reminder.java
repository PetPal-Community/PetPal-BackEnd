package com.ingsw.petpal.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDateTime;

@Data // Limpia los constructores
@Entity
@Table(name = "Recordatorios")
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tipoRecordatorio;
    private LocalDateTime hora;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "mascota_id",referencedColumnName = "id",foreignKey = @ForeignKey(name = "fk_pet_reminder") )
    private Pet mascota;

}

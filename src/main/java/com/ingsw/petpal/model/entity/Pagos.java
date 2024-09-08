package com.ingsw.petpal.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "books")
public class Pagos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String metodoPago;
    private String estadoPago;
    private LocalDateTime fechaPago;

    @Column(name = "monto")
    private BigDecimal valorPago;

    @ManyToOne
    @JoinColumn(name="contratacion_id",referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_contrato_pago"))
    private Contrats contrato;
}

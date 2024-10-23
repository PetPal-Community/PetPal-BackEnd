package com.ingsw.petpal.model.entity;

import com.ingsw.petpal.model.entity.enums.PaymentStatus;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pagos")
public class Pagos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String metodoPago;

    @Enumerated(EnumType.STRING)
    @Column(name = "estadoPago")
    private PaymentStatus estadoPago;

    private LocalDateTime fechaPago;


    private BigDecimal valorPago;

    private String estado;

    private Integer contratacionIdd;
    @ManyToOne
    @JoinColumn(name="contratacion_id",referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_contrato_pago"))
    private Contrats contrato;
}

package com.ingsw.petpal.model.entity;

import jakarta.persistence.*;

import lombok.Data;
import org.springframework.format.number.money.MonetaryAmountFormatter;

import java.math.BigDecimal;

@Data
@Entity
@Table(name="services")
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tipo_servicio;

    private String descripcion;

    private BigDecimal precio;

    private String picRuta;

    //private String ImgUrl;

    @ManyToOne
    @JoinColumn(name = "cuidador_id",referencedColumnName = "id", foreignKey = @ForeignKey(name="fk_cuidador_servicio"))
    private Carer cuidador;

}

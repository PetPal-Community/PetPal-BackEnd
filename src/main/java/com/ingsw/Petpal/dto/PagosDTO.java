package com.ingsw.Petpal.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PagosDTO {
    private Integer id;
    private String metodoPago;
    private String estadoPago;
    private LocalDateTime fechaPago;
}

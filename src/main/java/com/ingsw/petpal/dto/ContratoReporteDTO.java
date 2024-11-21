package com.ingsw.petpal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContratoReporteDTO {
    private int totalContracts; // Total de contratos
    private BigDecimal totalAmount;     // Total de dinero generado
}

package com.ingsw.petpal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PagosDTO {

    private Integer id;

    @NotBlank(message = "El método de pago es obligatorio")
    @Size(max = 30, message = "El método de pago debe tener 30 caracteres o menos")
    private String metodoPago;

    @NotBlank(message = "El estado del pago es obligatorio")
    @Size(max = 20, message = "El estado del pago debe tener 20 caracteres o menos")
    private String estadoPago;

    @NotNull(message = "La fecha del pago es obligatoria")
    @PastOrPresent(message = "La fecha del pago no puede estar en el futuro")
    private LocalDateTime fechaPago;

    private String estado;

    @NotNull(message = "El monto del pago es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor que cero")
    private BigDecimal valorPago;

    //
    @NotNull(message = "El ID del contrato es obligatorio")
    private Integer contratacionIdd;
}

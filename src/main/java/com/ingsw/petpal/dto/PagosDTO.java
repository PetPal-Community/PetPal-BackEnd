package com.ingsw.petpal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.*;
import lombok.Data;

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
}

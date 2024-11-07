package com.ingsw.petpal.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class ServicesDTO {

    private Integer id;
    private String picRuta;
    @NotBlank(message = "El Tipo de servicio es obligatorio")
    @Size(min = 5, max = 50)
    private String tipo_servicio;

    @Size(max = 120)
    private String descripcion;


    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0.")
    private BigDecimal precio;

    @NotNull(message = "Cuidador es obligatorio")
    private Integer cuidador;
}

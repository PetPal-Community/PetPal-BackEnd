package com.ingsw.petpal.dto;

import jakarta.validation.constraints.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class MensajesCreateUpdateDTO {
    public Integer id;

    @NotBlank(message = "El mensaje debe tener contenido")
    @Size(max = 200, message = "El contenido no debe tener más de 200 caracteres")
    private String contenido;

    private LocalDateTime fechaEnvio;

    private LocalDateTime fechaUpdate;

    @NotNull(message = "Usuario es obligatorio")
    private Integer usuarioGId;

    @NotNull(message = "Cuidador es obligatorio")
    private Integer cuidadorGId;

}

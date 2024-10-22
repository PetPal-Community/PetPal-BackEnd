package com.ingsw.petpal.dto;
import com.ingsw.petpal.model.entity.enums.ERoles;
import lombok.Data;

@Data
public class UserGeneralProfileDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private ERoles rol;
}

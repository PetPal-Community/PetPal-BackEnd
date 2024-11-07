package com.ingsw.petpal.mapper;

import com.ingsw.petpal.dto.AuthResponseDTO;
import com.ingsw.petpal.dto.UserGeneralProfileDTO;
import com.ingsw.petpal.dto.UserGeneralRegistration;
import com.ingsw.petpal.model.entity.User;
import com.ingsw.petpal.model.entity.UserGeneral;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserGeneralMapper {
    private final ModelMapper modelMapper;

    public UserGeneral toEntity(UserGeneralRegistration userGeneralRegistration){
        return modelMapper.map(userGeneralRegistration, UserGeneral.class);
    }

    public UserGeneralProfileDTO toDTO(UserGeneral user){
        UserGeneralProfileDTO userGeneralProfileDTO = modelMapper.map(user, UserGeneralProfileDTO.class);

        if(user.getUsuario() != null){
            userGeneralProfileDTO.setNombre(user.getUsuario().getNombre());
            userGeneralProfileDTO.setApellido(user.getUsuario().getApellido());
            userGeneralProfileDTO.setTelefono(user.getUsuario().getTelefono());

        }
        else if (user.getCarer() != null){
            userGeneralProfileDTO.setNombre(user.getCarer().getNombre());
            userGeneralProfileDTO.setApellido(user.getCarer().getApellido());
            userGeneralProfileDTO.setTelefono(user.getCarer().getTelefono());

        }
        userGeneralProfileDTO.setRol(user.getRole().getName());
        return userGeneralProfileDTO;
    }

    // Convertir General a User


    // Convertir de User a AuthResponseDTO para la respuesta de autenticación
    public AuthResponseDTO toAuthResponseDTO(UserGeneral user, String token) {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setToken(token); // Asignar el token
        authResponseDTO.setId(user.getId());

        // Si es cliente, asignar los datos de cliente
        if (user.getUsuario() != null) {

            authResponseDTO.setNombre(user.getUsuario().getNombre());
            authResponseDTO.setApellido(user.getUsuario().getApellido());
        }

        // Si es autor, asignar los datos de autor
        else if (user.getCarer() != null) {
            authResponseDTO.setNombre(user.getCarer().getNombre());
            authResponseDTO.setApellido(user.getCarer().getApellido());
        }

        // Para cualquier usuario que no sea cliente ni autor (ej. Admin)
        else {
            authResponseDTO.setNombre("Admin");
            authResponseDTO.setApellido("User");
        }

        // Asignar el rol del usuario
        authResponseDTO.setRole(user.getRole().getName().toString());

        return authResponseDTO;
    }

}

package com.ingsw.petpal.mapper;

import com.ingsw.petpal.dto.UsuarioDTO;
import com.ingsw.petpal.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    private final ModelMapper modelMapper;

    public UsuarioMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // Convierte el Modelo Usuario a usuarioDTO
    public UsuarioDTO toDTO(User usuario) {
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    // Convierte UsuarioDTO a Modelo Usuario
    public User toEntity(UsuarioDTO usuarioDTO) {
        return modelMapper.map(usuarioDTO, User.class);
    }
}
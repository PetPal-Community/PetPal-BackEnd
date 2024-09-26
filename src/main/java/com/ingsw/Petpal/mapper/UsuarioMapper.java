package com.ingsw.Petpal.mapper;

import com.ingsw.Petpal.dto.UsuarioDTO;
import com.ingsw.Petpal.model.entity.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    private final ModelMapper modelMapper;

    public UsuarioMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // Convierte el Modelo Usuario a usuarioDTO
    public UsuarioDTO toDTO(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    // Convierte UsuarioDTO a Modelo Usuario
    public Usuario toEntity(UsuarioDTO usuarioDTO) {
        return modelMapper.map(usuarioDTO, Usuario.class);
    }
}

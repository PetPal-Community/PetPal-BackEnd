package com.ingsw.Petpal.mapper;

import com.ingsw.Petpal.dto.ComunidadDTO;
import com.ingsw.Petpal.model.entity.Comunidad;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ComunidadMapper {

    private final ModelMapper modelMapper;

    public ComunidadMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ComunidadDTO toDTO(Comunidad comunidad) {
        return modelMapper.map(comunidad, ComunidadDTO.class);
    }

    public Comunidad toEntity(ComunidadDTO comunidadDTO) {
        return modelMapper.map(comunidadDTO, Comunidad.class);
    }
}

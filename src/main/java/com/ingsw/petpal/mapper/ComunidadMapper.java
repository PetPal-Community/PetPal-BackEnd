package com.ingsw.petpal.mapper;

import com.ingsw.petpal.dto.CommunityDTO;
import com.ingsw.petpal.model.entity.Community;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ComunidadMapper {

    private final ModelMapper modelMapper;

    public ComunidadMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CommunityDTO toDTO(Community comunidad) {
        return modelMapper.map(comunidad, CommunityDTO.class);
    }

    public Community toEntity(CommunityDTO comunidadDTO) {
        return modelMapper.map(comunidadDTO, Community.class);
    }
}
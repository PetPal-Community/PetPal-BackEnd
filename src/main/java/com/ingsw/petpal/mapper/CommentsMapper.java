package com.ingsw.petpal.mapper;

import com.ingsw.petpal.dto.ComentsCreateUpdateDTO;
import com.ingsw.petpal.dto.ComentsDetailsDTO;
import com.ingsw.petpal.model.entity.coments;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class CommentsMapper {
    private ModelMapper modelMapper;
    public CommentsMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        //configura model mapper para usar estrategia de coindcidencia estrcta
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public ComentsDetailsDTO toDetailsDTO(coments comentarios) {
        ComentsDetailsDTO comentsDetailsDTO = modelMapper.map(comentarios, ComentsDetailsDTO.class);
        comentsDetailsDTO.setUsuarioNombre(comentarios.getUsuario().getNombre() + ' ' + comentarios.getUsuario().getApellido());
        comentsDetailsDTO.setPublicacionDeNombre(comentarios.getPublicacion().getUsuario().getNombre() + ' ' + comentarios.getPublicacion().getUsuario().getApellido());
        return comentsDetailsDTO;
    }

    public coments toEntity(ComentsCreateUpdateDTO comentsCreateUpdateDTO) {
        return modelMapper.map(comentsCreateUpdateDTO, coments.class);
    }

    public ComentsCreateUpdateDTO toDTO(coments comentarios) {
        return modelMapper.map(comentarios, ComentsCreateUpdateDTO.class);
    }
}

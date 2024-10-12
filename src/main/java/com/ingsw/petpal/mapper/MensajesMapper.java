package com.ingsw.petpal.mapper;

import com.ingsw.petpal.dto.MensajeDetailsDTO;
import com.ingsw.petpal.dto.MensajesCreateUpdateDTO;
import com.ingsw.petpal.dto.TreatmentCreateUpdateDTO;
import com.ingsw.petpal.model.entity.Mensajes;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class MensajesMapper {
    private final ModelMapper modelMapper;
    public MensajesMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public MensajeDetailsDTO toDetailsDTO(Mensajes mensajes){
        MensajeDetailsDTO mensajeDetailsDTO = modelMapper.map(mensajes, MensajeDetailsDTO.class);
        mensajeDetailsDTO.setUsuarioNombre(mensajes.getUsuario().getNombre()+' '+mensajes.getUsuario().getApellido());
        mensajeDetailsDTO.setCuidadorNombre(mensajes.getCuidador().getNombre()+' '+mensajes.getCuidador().getApellido());
        return mensajeDetailsDTO;
    }

    public Mensajes toEntity(MensajesCreateUpdateDTO mensajesCreateUpdateDTO){
        return modelMapper.map(mensajesCreateUpdateDTO, Mensajes.class);
    }

    public MensajesCreateUpdateDTO toDTO(Mensajes mensajes){
        return modelMapper.map(mensajes, MensajesCreateUpdateDTO.class);
    }
}

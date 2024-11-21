package com.ingsw.petpal.mapper;

import com.ingsw.petpal.dto.ReviewDetailsDTO;
import com.ingsw.petpal.dto.ServicesDTO;
import com.ingsw.petpal.dto.ServicesDetailsDTO;
import com.ingsw.petpal.model.entity.Services;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ServicesMapper {

    private final ModelMapper modelMapper;

    public ServicesMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public ServicesDetailsDTO toDetailsDTO (Services services){
        ServicesDetailsDTO servicesDetailsDTO = modelMapper.map(services, ServicesDetailsDTO.class);
        servicesDetailsDTO.setCuidadorNombre(services.getCuidador().getNombre());
        servicesDetailsDTO.setPicRutaCuidador(services.getCuidador().getPicRuta());
        return servicesDetailsDTO;
    }

    public Services toEntity(ServicesDTO servicesDTO){
        return modelMapper.map(servicesDTO, Services.class);
    }

    public ServicesDTO toDTO(Services services){
        return modelMapper.map(services, ServicesDTO.class);
    }
}

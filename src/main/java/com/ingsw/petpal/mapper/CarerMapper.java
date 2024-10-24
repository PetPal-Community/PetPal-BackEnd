package com.ingsw.petpal.mapper;

import com.ingsw.petpal.dto.CarerDTO;
import com.ingsw.petpal.model.entity.Carer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CarerMapper {

    private final ModelMapper modelMapper;

    public CarerMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public CarerDTO toDTO (Carer carer){
        return  modelMapper.map(carer, CarerDTO.class);
    }

    public Carer toEntity(CarerDTO carerDTO){
        return modelMapper.map(carerDTO, Carer.class);
    }
}

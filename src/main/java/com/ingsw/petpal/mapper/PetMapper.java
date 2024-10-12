package com.ingsw.petpal.mapper;

import com.ingsw.petpal.dto.PetDTO;
import com.ingsw.petpal.model.entity.Pet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PetMapper {

    @Autowired
    private ModelMapper modelMapper;

    public PetDTO toDTO(Pet pet) {
        return modelMapper.map(pet, PetDTO.class);
    }

    public Pet toEntity(PetDTO petDTO) {
        return modelMapper.map(petDTO, Pet.class);
    }
}
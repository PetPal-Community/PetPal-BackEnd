package com.ingsw.petpal.mapper;

import com.ingsw.petpal.dto.PetCreateUpdateDTO;
import com.ingsw.petpal.dto.PetDetailsDTO;
import com.ingsw.petpal.model.entity.Pet;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class PetMapper {
    private final ModelMapper modelMapper;

    public PetMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        // Configura el model mapper para usar estrategia de coincidencia estricta
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public PetDetailsDTO toDetailsDTO(Pet pet) {
        PetDetailsDTO petDetailsDTO = modelMapper.map(pet, PetDetailsDTO.class);
        petDetailsDTO.setUsuarioId(pet.getUsuario() != null ? pet.getUsuario().getId().toString() : null); // Asigna el ID del usuario como String
        return petDetailsDTO;
    }

    public Pet toEntity(PetCreateUpdateDTO petCreateUpdateDTO) {
        return modelMapper.map(petCreateUpdateDTO, Pet.class);
    }

    public PetCreateUpdateDTO toDTO(Pet pet) {
        return modelMapper.map(pet, PetCreateUpdateDTO.class);
    }
}

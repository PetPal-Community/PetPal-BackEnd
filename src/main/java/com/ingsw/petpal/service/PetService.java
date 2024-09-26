package com.ingsw.petpal.service;

import com.ingsw.petpal.dto.PetDTO;

import java.util.List;

public interface PetService {
    List<PetDTO> getAllPets();
    PetDTO findPetById(Integer id);
    PetDTO createPet(PetDTO petDTO);
    PetDTO updatePet(Integer id, PetDTO updatedPetDTO);
    void deletePet(Integer id);
}

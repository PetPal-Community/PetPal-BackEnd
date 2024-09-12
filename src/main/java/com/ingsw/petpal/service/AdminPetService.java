package com.ingsw.petpal.service;

import com.ingsw.petpal.model.entity.Pet;
import java.util.List;

public interface AdminPetService {
    Pet createPet(Pet pet);
    Pet updatePet(Pet pet);
    void deletePet(Integer id);
    Pet getPetById(Integer id);
    List<Pet> getAllPets();
}

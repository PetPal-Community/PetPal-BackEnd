package com.ingsw.petpal.service.impl;

import com.ingsw.petpal.model.entity.Pet;
import com.ingsw.petpal.repository.PetRepository;
import com.ingsw.petpal.service.AdminPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminPetServiceImpl implements AdminPetService {

    @Autowired
    private PetRepository petRepository;

    @Override
    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public Pet updatePet(Pet pet) {
        return petRepository.save(pet); // Save sirve tanto para crear como para actualizar.
    }

    @Override
    public void deletePet(Integer id) {
        petRepository.deleteById(id);
    }

    @Override
    public Pet getPetById(Integer id) {
        return petRepository.findById(id).orElse(null);
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }
}

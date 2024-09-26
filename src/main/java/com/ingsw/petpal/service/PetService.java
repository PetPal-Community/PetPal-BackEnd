package com.ingsw.petpal.service;

import com.ingsw.petpal.model.entity.Pet;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PetService {
    List<Pet> findAll();
    Pet create(Pet pet);
    Pet findById(Integer id);
    Pet update(Pet pet);

    @Transactional
    Pet update(Integer id, Pet updatePet);

    void delete(Integer id);
}

package com.ingsw.petpal.controller;

import com.ingsw.petpal.model.entity.Pet;
import com.ingsw.petpal.service.AdminPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private AdminPetService adminPetService;

    @PostMapping
    public Pet createPet(@RequestBody Pet pet) {
        return adminPetService.createPet(pet);
    }

    @PutMapping("/{id}")
    public Pet updatePet(@PathVariable Integer id, @RequestBody Pet pet) {
        pet.setId(id);
        return adminPetService.updatePet(pet);
    }

    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable Integer id) {
        adminPetService.deletePet(id);
    }

    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable Integer id) {
        return adminPetService.getPetById(id);
    }

    @GetMapping
    public List<Pet> getAllPets() {
        return adminPetService.getAllPets();
    }
}

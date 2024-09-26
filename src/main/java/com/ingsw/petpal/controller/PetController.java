package com.ingsw.petpal.controller;

import com.ingsw.petpal.model.entity.Pet;
import com.ingsw.petpal.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService adminPetService;

    @GetMapping
    public List<Pet> list() {
        return adminPetService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Pet create(@RequestBody Pet pet) {
        return adminPetService.create(pet);
    }

    @GetMapping("/{id}")
    public Pet get(@PathVariable Integer id){
        return adminPetService.findById(id);
    }

    @PutMapping("/{id}")
    public Pet update(@PathVariable Integer id, @RequestBody Pet pet){
        return adminPetService.update(id, pet);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        adminPetService.delete(id);
    }
}
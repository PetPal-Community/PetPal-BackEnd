package com.ingsw.petpal.controller;

import com.ingsw.petpal.dto.PetCreateUpdateDTO;
import com.ingsw.petpal.dto.PetDetailsDTO;
import com.ingsw.petpal.service.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pets")
public class PetController {
    private final PetService petService;

    @GetMapping
    public ResponseEntity<List<PetDetailsDTO>> getAllPets() {
        List<PetDetailsDTO> pets = petService.findAll();
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDetailsDTO> getPetById(@PathVariable("id") Integer id) {
        PetDetailsDTO pet = petService.findById(id);
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PetDetailsDTO> createPet(@Valid @RequestBody PetCreateUpdateDTO petCreateUpdateDTO) {
        PetDetailsDTO createdPet = petService.create(petCreateUpdateDTO);
        return new ResponseEntity<>(createdPet, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetDetailsDTO> updatePet(@PathVariable Integer id, @Valid @RequestBody PetCreateUpdateDTO petCreateUpdateDTO) {
        PetDetailsDTO updatedPet = petService.update(id, petCreateUpdateDTO);
        return new ResponseEntity<>(updatedPet, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Integer id) {
        petService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.ingsw.petpal.service.impl;

import com.ingsw.petpal.dto.PetDTO;
import com.ingsw.petpal.exception.ResourceNotFoundException;
import com.ingsw.petpal.mapper.PetMapper;
import com.ingsw.petpal.model.entity.Pet;
import com.ingsw.petpal.repository.PetRepository;
import com.ingsw.petpal.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final PetMapper petMapper;

    @Transactional(readOnly = true)
    @Override
    public List<PetDTO> getAllPets() {
        List<Pet> pets = petRepository.findAll();
        return pets.stream()
                .map(petMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public PetDTO findPetById(Integer id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada"));
        return petMapper.toDTO(pet);
    }

    @Transactional
    @Override
    public PetDTO createPet(PetDTO petDTO) {
        Pet pet = petMapper.toEntity(petDTO);
        pet = petRepository.save(pet);
        return petMapper.toDTO(pet);
    }

    @Transactional
    @Override
    public PetDTO updatePet(Integer id, PetDTO updatedPetDTO) {
        Pet petFromDb = petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada"));

        // Actualiza las propiedades de la mascota según lo que venga en el DTO
        petFromDb.setRaza(updatedPetDTO.getRaza());
        petFromDb.setEdad(updatedPetDTO.getEdad());
        petFromDb.setGenero(updatedPetDTO.getGenero());
        petFromDb.setEspecie(updatedPetDTO.getEspecie());

        petFromDb = petRepository.save(petFromDb);
        return petMapper.toDTO(petFromDb);
    }

    @Transactional
    @Override
    public void deletePet(Integer id) {
        Pet petFromDb = petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada"));
        petRepository.delete(petFromDb);
    }
}

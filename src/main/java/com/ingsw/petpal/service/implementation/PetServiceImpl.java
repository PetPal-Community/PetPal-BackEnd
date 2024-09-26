package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.exception.ResourceNotFoundException;
import com.ingsw.petpal.model.entity.Pet;
import com.ingsw.petpal.model.entity.User;
import com.ingsw.petpal.repository.PetRepository;
import com.ingsw.petpal.repository.UserRepository;
import com.ingsw.petpal.service.PetService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    @Transactional
    @Override
    public Pet create(Pet pet) {
        User usuario = userRepository.findById(pet.getUsuario().getId()).orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + pet.getUsuario().getId()));
        pet.setUsuario(usuario);
        return petRepository.save(pet);
    }

    @Transactional(readOnly = true)
    @Override
    public Pet findById(Integer id) {
        return petRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user not found" + id));
    }

    @Override
    public Pet update(Pet pet) {
        return null;
    }

    @Transactional
    @Override
    public Pet update(Integer id, Pet updatePet) {
        User usuario = userRepository.findById(updatePet.getUsuario().getId()).orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + updatePet.getUsuario().getId()));
        Pet petFromDb = findById(id);
        petFromDb.setUsuario(usuario);
        petFromDb.setRaza(updatePet.getRaza());
        petFromDb.setNombre(updatePet.getNombre());
        petFromDb.setEdad(updatePet.getEdad());
        petFromDb.setGenero(updatePet.getGenero());
        petFromDb.setEspecie(updatePet.getEspecie());

        return petRepository.save(petFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Pet petFromDb = findById(id);
        petRepository.delete(petFromDb);
    }
}

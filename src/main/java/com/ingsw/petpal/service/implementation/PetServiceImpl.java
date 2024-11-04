package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.dto.PetCreateUpdateDTO;
import com.ingsw.petpal.dto.PetDetailsDTO;
import com.ingsw.petpal.exception.ResourceNotFoundException;
import com.ingsw.petpal.mapper.PetMapper;
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
    private final PetMapper petMapper;

    @Transactional(readOnly = true)
    @Override
    public List<PetDetailsDTO> findAll() {
        List<Pet> pets = petRepository.findAll();
        return pets.stream()
                .map(petMapper::toDetailsDTO)
                .toList();
    }

    @Transactional
    @Override
    public PetDetailsDTO create(PetCreateUpdateDTO petCreateUpdateDTO) {
        User user = userRepository.findById(petCreateUpdateDTO.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + petCreateUpdateDTO.getUsuarioId()));

        Pet pet = petMapper.toEntity(petCreateUpdateDTO);
        pet.setUsuario(user);  // Aquí se establece la relación con el usuario

        return petMapper.toDetailsDTO(petRepository.save(pet));
    }


    @Transactional(readOnly = true)
    @Override
    public PetDetailsDTO findById(int id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada con id: " + id));
        return petMapper.toDetailsDTO(pet);
    }

    @Transactional
    @Override
    public PetDetailsDTO update(Integer id, PetCreateUpdateDTO updatedPet) {
        Pet petFromDb = petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada con id: " + id));

        User user = userRepository.findById(updatedPet.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + updatedPet.getUsuarioId()));

        petFromDb.setNombre(updatedPet.getNombre());
        petFromDb.setRaza(updatedPet.getRaza());
        petFromDb.setEdad(updatedPet.getEdad());
        petFromDb.setGenero(updatedPet.getGenero());
        petFromDb.setEspecie(updatedPet.getEspecie());
        petFromDb.setUsuario(user);

        return petMapper.toDetailsDTO(petRepository.save(petFromDb));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada con id: " + id));
        petRepository.delete(pet);
    }
}

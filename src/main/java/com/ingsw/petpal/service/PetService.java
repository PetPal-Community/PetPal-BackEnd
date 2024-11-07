package com.ingsw.petpal.service;

import com.ingsw.petpal.dto.PetCreateUpdateDTO;
import com.ingsw.petpal.dto.PetDetailsDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PetService {
    List<PetDetailsDTO> findAllByUserId(Integer userId);
    List<PetDetailsDTO> findAll();

    PetDetailsDTO create(PetCreateUpdateDTO pet);

    PetDetailsDTO findById(int id);

    PetDetailsDTO update(Integer id, PetCreateUpdateDTO pet);

    void delete(Integer id);
}

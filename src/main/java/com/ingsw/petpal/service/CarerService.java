package com.ingsw.petpal.service;

import com.ingsw.petpal.dto.CarerDTO;
import com.ingsw.petpal.dto.UsuarioDTO;
import com.ingsw.petpal.model.entity.Carer;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CarerService {

    List<CarerDTO> getAll();

    CarerDTO findById(Integer id);

    CarerDTO create(CarerDTO carerDTO);

    CarerDTO update(Integer id, CarerDTO updatedCarerDTO);

    void delete(Integer id);
}
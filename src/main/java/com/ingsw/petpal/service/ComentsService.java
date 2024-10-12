package com.ingsw.petpal.service;

import com.ingsw.petpal.dto.ComentsCreateUpdateDTO;
import com.ingsw.petpal.dto.ComentsDetailsDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ComentsService {
    List<ComentsDetailsDTO> findAll();

    ComentsDetailsDTO findById(Integer id);

    ComentsDetailsDTO create(ComentsCreateUpdateDTO commentsDTO);

    @Transactional
    ComentsDetailsDTO update(Integer id, ComentsCreateUpdateDTO updatedCommentsDTO);

    void delete(Integer id);
}

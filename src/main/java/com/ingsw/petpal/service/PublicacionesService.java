package com.ingsw.petpal.service;

import com.ingsw.petpal.dto.PublicacionCreateDTO;
import com.ingsw.petpal.dto.PublicacionDetailsDTO;
import com.ingsw.petpal.model.entity.Publicaciones;
import com.ingsw.petpal.model.entity.Services;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PublicacionesService {
    List<PublicacionDetailsDTO> findAll();
    PublicacionDetailsDTO create(PublicacionCreateDTO publicaciones);
    PublicacionDetailsDTO findById(Integer id);

    PublicacionDetailsDTO update(Integer id, PublicacionCreateDTO updatepublicaciones);

    void delete(Integer id);
}

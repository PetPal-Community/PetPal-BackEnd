package com.ingsw.Petpal.service;

import com.ingsw.Petpal.dto.ComunidadDTO;

import java.util.List;

public interface ComunidadService {
    List<ComunidadDTO> getAll();

    ComunidadDTO findById(Integer id);

    ComunidadDTO create(ComunidadDTO comunidadDTO);

    ComunidadDTO update(Integer id, ComunidadDTO updatedComunidadDTO);

    void delete(Integer id);
}

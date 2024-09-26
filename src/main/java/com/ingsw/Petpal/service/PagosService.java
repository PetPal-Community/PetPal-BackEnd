package com.ingsw.Petpal.service;


import com.ingsw.Petpal.dto.PagosDTO;

import java.util.List;

public interface PagosService {
    List<PagosDTO> getAll();

    PagosDTO findById(Integer id);

    PagosDTO create(PagosDTO pagosDTO);

    PagosDTO update(Integer id, PagosDTO updatedPagosDTO);

    void delete(Integer id);
}

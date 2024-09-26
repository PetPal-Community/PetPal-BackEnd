package com.ingsw.petpal.service;

import com.ingsw.petpal.dto.MedicVisitDTO;

import java.util.List;

public interface MedicVisitService {
    List<MedicVisitDTO> getAll();

    MedicVisitDTO findById(Integer id);

    MedicVisitDTO create(MedicVisitDTO medicVisitDTO);

    MedicVisitDTO update(Integer id, MedicVisitDTO medicVisitDTO);

    void delete(Integer id);
}

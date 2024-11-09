package com.ingsw.petpal.service;

import com.ingsw.petpal.dto.MedicVisitCreateUpdateDTO;
import com.ingsw.petpal.dto.MedicVisitDetailsDTO;
import com.ingsw.petpal.model.entity.MedicVisit;

import java.util.List;

public interface MedicVisitService {
    List<MedicVisitDetailsDTO> findAll();

    MedicVisitDetailsDTO create(MedicVisitCreateUpdateDTO medicVisitCreateUpdateDTO);

    MedicVisitDetailsDTO findById(Integer id);

    MedicVisitDetailsDTO update(Integer id, MedicVisitCreateUpdateDTO updatedMedicVisit);

    void delete(Integer id);

    List<MedicVisitDetailsDTO> findMedicVisitByUser(Integer medicVisitId);
}

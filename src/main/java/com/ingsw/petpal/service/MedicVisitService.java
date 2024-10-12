package com.ingsw.petpal.service;

import com.ingsw.petpal.dto.MedicVisitDTO;
import com.ingsw.petpal.model.entity.MedicVisit;
import com.ingsw.petpal.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface MedicVisitService {
    List<MedicVisitDTO> getAll();

    MedicVisitDTO findById(Integer id);

    MedicVisitDTO create(MedicVisitDTO medicVisitDTO);

    MedicVisitDTO update(Integer id, MedicVisitDTO medicVisitDTO);

    void delete(Integer id);
}
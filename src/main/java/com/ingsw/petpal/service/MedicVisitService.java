package com.ingsw.petpal.service;

import com.ingsw.petpal.model.entity.MedicVisit;
import com.ingsw.petpal.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface MedicVisitService {

    List<MedicVisit> findAll();
    MedicVisit create(MedicVisit medicVisit);
    MedicVisit findById(Integer id);
    MedicVisit update(MedicVisit medicVisit);

    @Transactional
    MedicVisit update(Integer id, MedicVisit updateMedicVisit);

    void delete(Integer id);
}
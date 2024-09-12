package com.ingsw.petpal.service;

import com.ingsw.petpal.model.entity.MedicVisit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AdminMedicVisitService {

    MedicVisit createMedicVisit(MedicVisit medicVisit);

    Optional<MedicVisit> getMedicVisitById(Integer id);

    Page<MedicVisit> getAllMedicVisits(Pageable pageable);

    MedicVisit updateMedicVisit(Integer id, MedicVisit medicVisit);

    void deleteMedicVisit(Integer id);
}

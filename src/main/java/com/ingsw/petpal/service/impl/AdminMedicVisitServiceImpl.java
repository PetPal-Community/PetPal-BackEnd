package com.ingsw.petpal.service.impl;

import com.ingsw.petpal.model.entity.MedicVisit;
import com.ingsw.petpal.repository.MedicVisitRepository;
import com.ingsw.petpal.service.AdminMedicVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminMedicVisitServiceImpl implements AdminMedicVisitService {

    @Autowired
    private MedicVisitRepository medicVisitRepository;

    @Override
    public MedicVisit createMedicVisit(MedicVisit medicVisit) {
        return medicVisitRepository.save(medicVisit);
    }

    @Override
    public Optional<MedicVisit> getMedicVisitById(Integer id) {
        return medicVisitRepository.findById(id);
    }

    @Override
    public Page<MedicVisit> getAllMedicVisits(Pageable pageable) {
        return medicVisitRepository.findAll(pageable);
    }

    @Override
    public MedicVisit updateMedicVisit(Integer id, MedicVisit medicVisit) {
        if (medicVisitRepository.existsById(id)) {
            medicVisit.setId(id);
            return medicVisitRepository.save(medicVisit);
        }
        return null;
    }

    @Override
    public void deleteMedicVisit(Integer id) {
        medicVisitRepository.deleteById(id);
    }
}

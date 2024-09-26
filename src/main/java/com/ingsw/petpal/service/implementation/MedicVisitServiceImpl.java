package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.exception.ResourceNotFoundException;
import com.ingsw.petpal.model.entity.MedicVisit;
import com.ingsw.petpal.model.entity.Pet;
import com.ingsw.petpal.model.entity.User;
import com.ingsw.petpal.repository.MedicVisitRepository;
import com.ingsw.petpal.repository.PetRepository;
import com.ingsw.petpal.repository.UserRepository;
import com.ingsw.petpal.service.MedicVisitService;
import com.ingsw.petpal.service.PetService;
import com.ingsw.petpal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MedicVisitServiceImpl implements MedicVisitService {

    private final MedicVisitRepository medicVisitRepository;
    private final PetRepository petRepository;

    @Transactional(readOnly = true)
    @Override
    public List<MedicVisit> findAll() {
        return medicVisitRepository.findAll();
    }

    @Transactional
    @Override
    public MedicVisit create(MedicVisit medicVisit) {
        Pet pet = petRepository.findById(medicVisit.getMascota().getId()).orElseThrow(() -> new ResourceNotFoundException("Mascota not found with id: " + medicVisit.getMascota().getId()));
        medicVisit.setMascota(pet);
        return medicVisitRepository.save(medicVisit);
    }

    @Transactional(readOnly = true)
    @Override
    public MedicVisit findById(Integer id) {
        return medicVisitRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("medicVisit not found" + id));
    }

    @Override
    public MedicVisit update(MedicVisit medicVisit) {
        return null;
    }

    @Transactional
    @Override
    public MedicVisit update(Integer id, MedicVisit updateMedicVisit) {
        MedicVisit medicVisitFromDb = findById(id);
        Pet pet = petRepository.findById(updateMedicVisit.getMascota().getId()).orElseThrow(() -> new ResourceNotFoundException("Mascota not found with id: " + updateMedicVisit.getMascota().getId()));
        medicVisitFromDb.setDiagnostico(updateMedicVisit.getDiagnostico());
        medicVisitFromDb.setFechaVisita(updateMedicVisit.getFechaVisita());
        medicVisitFromDb.setMascota(pet);
        return medicVisitRepository.save(medicVisitFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        MedicVisit medicVisitFromDb = findById(id);
        medicVisitRepository.delete(medicVisitFromDb);
    }
}
package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.model.entity.MedicDocuments;
import com.ingsw.petpal.model.entity.Treatment;
import com.ingsw.petpal.repository.TreatmentRepository;
import com.ingsw.petpal.service.TreatmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TreatmentServiceImplementation implements TreatmentService {

    private final TreatmentRepository treatmentRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Treatment> findAll() {
        return treatmentRepository.findAll();
    }

    @Transactional
    @Override
    public Treatment create(Treatment treatment) {
        treatment.setFechaInico(LocalDate.from(LocalDateTime.now()));
        treatment.setFechaFin(LocalDate.from(LocalDateTime.now()));
        return treatmentRepository.save(treatment);
    }

    @Transactional(readOnly = true)
    @Override
    public Treatment findById(int id) {
        return treatmentRepository.findById(id).orElseThrow(()-> new RuntimeException("Treatment not found" + id));

    }


    @Override
    public Treatment update(Treatment treatment) {
        return null;
    }


    @Transactional
    @Override
    public Treatment update(Integer id, Treatment updatedTreatment) {
        Treatment treatmentFromDb = findById(id);

        treatmentFromDb.setDescripcion(updatedTreatment.getDescripcion());
        treatmentFromDb.setMedicamento(updatedTreatment.getMedicamento());
        treatmentFromDb.setDosis(updatedTreatment.getDosis());

        return treatmentRepository.save(treatmentFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Treatment treatmentFromDb = findById(id);
        treatmentRepository.delete(treatmentFromDb);
    }
}
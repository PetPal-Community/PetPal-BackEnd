package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.dto.TreatmentCreateUpdateDTO;
import com.ingsw.petpal.dto.TreatmentDetailsDTO;
import com.ingsw.petpal.exception.ResourceNotFoundException;
import com.ingsw.petpal.mapper.TreatmentMapper;
import com.ingsw.petpal.model.entity.MedicVisit;
import com.ingsw.petpal.model.entity.Treatment;
import com.ingsw.petpal.repository.MedicVisitRepository;
import com.ingsw.petpal.repository.TreatmentRepository;
import com.ingsw.petpal.service.TreatmentService;
import jakarta.persistence.ManyToOne;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@RequiredArgsConstructor
@Service
public class TreatmentServiceImplementation implements TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final MedicVisitRepository medicVisitRepository;
    private final TreatmentMapper treatmentMapper;

    @Transactional(readOnly = true)
    @Override
    public List<TreatmentDetailsDTO> findAll() {
        List<Treatment> treatments = treatmentRepository.findAll();
        return treatments.stream()
                .map(treatmentMapper::toDetailsDTO)
                .toList();
    }

    @Transactional
    @Override
    public TreatmentDetailsDTO create(TreatmentCreateUpdateDTO treatmentCreateUpdateDTO) {

        MedicVisit medicVisit = medicVisitRepository.findById(treatmentCreateUpdateDTO.getVisitaMedica()).orElseThrow(() -> new ResourceNotFoundException("Visitamedica not found with id: " + treatmentCreateUpdateDTO.getVisitaMedica()));
        Treatment treatment= treatmentMapper.toEntity(treatmentCreateUpdateDTO);
        treatment.setFechaInico(treatmentCreateUpdateDTO.getFechaInicio());
        treatment.setFechaFin(treatmentCreateUpdateDTO.getFechaFin());
        treatment.setVisitaMedica(medicVisit);

        return treatmentMapper.toDetailsDTO(treatmentRepository.save(treatment));
    }

    @Transactional(readOnly = true)
    @Override
    public TreatmentDetailsDTO findById(int id) {
        //Treatment treatment = treatmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Treatmentnot found with id: " + id));
        Treatment treatment = treatmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Treatmentnot found with id: " + id));
        return treatmentMapper.toDetailsDTO(treatment);

    }


    @Override
    public TreatmentDetailsDTO update(TreatmentCreateUpdateDTO treatment) {
        return null;
    }


    @Transactional
    @Override
    public TreatmentDetailsDTO update(Integer id, TreatmentCreateUpdateDTO updatedTreatment) {
        Treatment treatmentFromDb = treatmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Treatmentnot found with id: " + id));
        MedicVisit medicVisit = medicVisitRepository.findById(updatedTreatment.getVisitaMedica()).orElseThrow(() -> new ResourceNotFoundException("MedicVisit not found with id: " + updatedTreatment.getVisitaMedica()));


        treatmentFromDb.setVisitaMedica(medicVisit);
        treatmentFromDb.setDescripcion(updatedTreatment.getDescripcion());
        treatmentFromDb.setMedicamento(updatedTreatment.getMedicamento());
        treatmentFromDb.setDosis(updatedTreatment.getDosis());
        treatmentFromDb.setFechaInico(updatedTreatment.getFechaInicio());
        treatmentFromDb.setFechaFin(updatedTreatment.getFechaFin());

        return treatmentMapper.toDetailsDTO(treatmentRepository.save(treatmentFromDb));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Treatment treatment = treatmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Treatmentnot found with id: " + id));
        treatmentRepository.delete(treatment);
    }
}

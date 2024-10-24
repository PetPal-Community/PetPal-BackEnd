package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.dto.MedicVisitCreateUpdateDTO;
import com.ingsw.petpal.dto.MedicVisitDetailsDTO;
import com.ingsw.petpal.exception.ResourceNotFoundException;
import com.ingsw.petpal.mapper.MedicVisitMapper;
import com.ingsw.petpal.model.entity.MedicVisit;
import com.ingsw.petpal.repository.MedicVisitRepository;
import com.ingsw.petpal.service.MedicVisitService;
import jakarta.persistence.ManyToOne;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MedicVisitServiceImpl implements MedicVisitService {

    private final MedicVisitRepository medicVisitRepository;
    private final MedicVisitMapper medicVisitMapper;

    @Transactional(readOnly = true)
    @Override
    public List<MedicVisitDetailsDTO> findAll() {
        List<MedicVisit> visits = medicVisitRepository.findAll();
        return visits.stream()
                .map(medicVisitMapper::toDetailsDTO)
                .toList();
    }

    @Transactional
    @Override
    public MedicVisitDetailsDTO create(MedicVisitCreateUpdateDTO medicVisitCreateUpdateDTO) {
        MedicVisit medicVisit = medicVisitMapper.toEntity(medicVisitCreateUpdateDTO);
        return medicVisitMapper.toDetailsDTO(medicVisitRepository.save(medicVisit));
    }

    @Transactional(readOnly = true)
    @Override
    public MedicVisitDetailsDTO findById(Integer id) {
        MedicVisit medicVisit = medicVisitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medic visit not found with id: " + id));
        return medicVisitMapper.toDetailsDTO(medicVisit);
    }

    @Transactional
    @Override
    public MedicVisitDetailsDTO update(Integer id, MedicVisitCreateUpdateDTO updatedMedicVisit) {
        MedicVisit medicVisitFromDb = medicVisitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medic visit not found with id: " + id));

        medicVisitFromDb.setDiagnostico(updatedMedicVisit.getDiagnostico());
        medicVisitFromDb.setFechaVisita(updatedMedicVisit.getFechaVisita());


        return medicVisitMapper.toDetailsDTO(medicVisitRepository.save(medicVisitFromDb));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        MedicVisit medicVisit = medicVisitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medic visit not found with id: " + id));
        medicVisitRepository.delete(medicVisit);
    }
}

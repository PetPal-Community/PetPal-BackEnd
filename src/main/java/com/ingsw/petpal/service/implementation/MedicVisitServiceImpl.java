package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.dto.MedicVisitDTO;
import com.ingsw.petpal.exception.ResourceNotFoundException;
import com.ingsw.petpal.mapper.MedicVisitMapper;
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

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MedicVisitServiceImpl implements MedicVisitService {

    private final MedicVisitRepository medicVisitRepository;
    private final MedicVisitMapper medicVisitMapper;

    @Transactional(readOnly = true)
    @Override
    public List<MedicVisitDTO> getAll() {
        List<MedicVisit> visits = medicVisitRepository.findAll();
        return visits.stream()
                .map(medicVisitMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public MedicVisitDTO findById(Integer id) {
        MedicVisit visit = medicVisitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Visita médica no encontrada"));
        return medicVisitMapper.toDTO(visit);
    }

    @Transactional
    @Override
    public MedicVisitDTO create(MedicVisitDTO medicVisitDTO) {
        MedicVisit visit = medicVisitMapper.toEntity(medicVisitDTO);
        visit = medicVisitRepository.save(visit);
        return medicVisitMapper.toDTO(visit);
    }

    @Transactional
    @Override
    public MedicVisitDTO update(Integer id, MedicVisitDTO medicVisitDTO) {
        MedicVisit visitFromDb = medicVisitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Visita médica no encontrada"));

        // Actualiza los campos que necesites aquí

        visitFromDb = medicVisitRepository.save(visitFromDb);
        return medicVisitMapper.toDTO(visitFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        MedicVisit visitFromDb = medicVisitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Visita médica no encontrada"));
        medicVisitRepository.delete(visitFromDb);
    }
}
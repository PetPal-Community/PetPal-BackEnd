package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.dto.MedicDocumentsCreateUpdateDTO;
import com.ingsw.petpal.dto.MedicDocumentsDetailsDTO;

import com.ingsw.petpal.exception.ResourceNotFoundException;
import com.ingsw.petpal.mapper.MedicDocumentsMapper;
import com.ingsw.petpal.model.entity.MedicDocuments;
import com.ingsw.petpal.model.entity.MedicVisit;
import com.ingsw.petpal.repository.MedicDocumentsRepository;
import com.ingsw.petpal.repository.MedicVisitRepository;
import com.ingsw.petpal.service.MedicDocumentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MedicDocumentsImplementation implements MedicDocumentsService {

    private final MedicDocumentsRepository medicDocumentsRepository;
    private final MedicVisitRepository medicVisitRepository;
    private final MedicDocumentsMapper medicDocumentsMapper;

    @Transactional(readOnly = true)
    @Override
    public List<MedicDocumentsDetailsDTO> findAll() {
        List<MedicDocuments> medicDocuments = medicDocumentsRepository.findAll();
        return medicDocuments.stream()
                .map(medicDocumentsMapper::toDetailsDTO)
                .toList();
    }

    @Transactional
    @Override
    public MedicDocumentsDetailsDTO create(MedicDocumentsCreateUpdateDTO medicDocumentsCreateUpdateDTO) {
        MedicVisit medicVisit = medicVisitRepository.findById(medicDocumentsCreateUpdateDTO.getVisitaMedica()).orElseThrow(() -> new ResourceNotFoundException("MedicDoc not found with id: " + medicDocumentsCreateUpdateDTO.getVisitaMedica()));
        MedicDocuments medicDocuments = medicDocumentsMapper.toEntity(medicDocumentsCreateUpdateDTO);

        medicDocuments.setVisitamedica(medicVisit);
        return medicDocumentsMapper.toDetailsDTO(medicDocumentsRepository.save(medicDocuments));
    }


    @Transactional(readOnly = true)
    @Override
    public MedicDocumentsDetailsDTO findById(int id) {
        MedicDocuments medicDocuments = medicDocumentsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Medicdocuments found with id: " + id));
        return medicDocumentsMapper.toDetailsDTO(medicDocuments);
    }


    @Override
    public MedicDocumentsDetailsDTO update(MedicDocumentsCreateUpdateDTO medicDocuments) {
        return null;
    }

    @Transactional
    @Override
    public MedicDocumentsDetailsDTO update(Integer id, MedicDocumentsCreateUpdateDTO updatedMedicDocuments) {
        MedicDocuments medicFromDb = medicDocumentsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Medic Visit found with id: " + id));
        MedicVisit medicVisit = medicVisitRepository.findById(updatedMedicDocuments.getVisitaMedica()).orElseThrow(() -> new ResourceNotFoundException("Mascota not found with id: " + updatedMedicDocuments.getVisitaMedica()));

        medicFromDb.setTipoDocumento(updatedMedicDocuments.getTipoDocumento());
        medicFromDb.setDescripcion(updatedMedicDocuments.getDescripcion());
        medicFromDb.setVisitamedica(medicVisit);
        return medicDocumentsMapper.toDetailsDTO(medicDocumentsRepository.save(medicFromDb));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        MedicDocuments medicFromDb = medicDocumentsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Medic Visit found with id: " + id));
        medicDocumentsRepository.delete(medicFromDb);
    }
}

package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.model.entity.MedicDocuments;
import com.ingsw.petpal.repository.MedicDocumentsRepository;
import com.ingsw.petpal.service.MedicDocumentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service

public class MedicDocumentsImplementation implements MedicDocumentsService {

    private final MedicDocumentsRepository medicDocumentsRepository;

    @Transactional(readOnly = true)
    @Override
    public List<MedicDocuments> findAll() {
        return medicDocumentsRepository.findAll();
    }

    @Transactional
    @Override
    public MedicDocuments create(MedicDocuments medicDocuments) {
        return medicDocumentsRepository.save(medicDocuments);
    }

    @Transactional(readOnly = true)
    @Override
    public MedicDocuments findById(int id) {
        return medicDocumentsRepository.findById(id).orElseThrow(()->new RuntimeException("Medic Documents not found" + id));
    }


    @Override
    public MedicDocuments update(MedicDocuments medicDocuments) {
        return null;
    }

    @Transactional
    @Override
    public MedicDocuments update(Integer id, MedicDocuments updatedMedicDocuments) {
        MedicDocuments medicFromDb = findById(id);
        medicFromDb.setTipoDocumento(updatedMedicDocuments.getTipoDocumento());
        medicFromDb.setDescripcion(updatedMedicDocuments.getDescripcion());
        return medicDocumentsRepository.save(medicFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        MedicDocuments medicFromDb = findById(id);
        medicDocumentsRepository.delete(medicFromDb);
    }
}

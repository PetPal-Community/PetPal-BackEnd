package com.ingsw.petpal.service;


import com.ingsw.petpal.model.entity.MedicDocuments;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MedicDocumentsService {

    List<MedicDocuments> findAll();
    MedicDocuments create(MedicDocuments medicDocuments);
    MedicDocuments findById(int id);
    MedicDocuments update(MedicDocuments medicDocuments);

    @Transactional
    MedicDocuments update(Integer id, MedicDocuments updatedMedicDocuments);

    void delete(Integer id);
}


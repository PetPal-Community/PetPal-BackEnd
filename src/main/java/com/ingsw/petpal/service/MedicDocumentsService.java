package com.ingsw.petpal.service;

import com.ingsw.petpal.dto.MedicDocumentsCreateUpdateDTO;
import com.ingsw.petpal.dto.MedicDocumentsDetailsDTO;
import com.ingsw.petpal.model.entity.MedicDocuments;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MedicDocumentsService {

    List<MedicDocumentsDetailsDTO> findAll();
    MedicDocumentsDetailsDTO create(MedicDocumentsCreateUpdateDTO medicDocuments);
    MedicDocumentsDetailsDTO findById(int id);
    MedicDocumentsDetailsDTO update(MedicDocumentsCreateUpdateDTO medicDocuments);

    @Transactional
    MedicDocumentsDetailsDTO update(Integer id, MedicDocumentsCreateUpdateDTO updatedMedicDocuments);

    void delete(Integer id);
}
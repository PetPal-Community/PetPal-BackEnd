package com.ingsw.petpal.service;

import com.ingsw.petpal.dto.TreatmentCreateUpdateDTO;
import com.ingsw.petpal.dto.TreatmentDetailsDTO;
import com.ingsw.petpal.model.entity.Treatment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TreatmentService {
    List<TreatmentDetailsDTO> findAll();


    TreatmentDetailsDTO create(TreatmentCreateUpdateDTO treatment);
    TreatmentDetailsDTO findById(int id);
    TreatmentDetailsDTO update(TreatmentCreateUpdateDTO treatment);

    @Transactional
    TreatmentDetailsDTO update(Integer id, TreatmentCreateUpdateDTO treatment);

    void delete(Integer id);

}

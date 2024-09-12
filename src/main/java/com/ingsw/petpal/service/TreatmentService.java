package com.ingsw.petpal.service;

import com.ingsw.petpal.model.entity.Treatment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TreatmentService {
    List<Treatment> findAll();


    Treatment create(Treatment treatment);
    Treatment findById(int id);
    Treatment update(Treatment treatment);

    @Transactional
    Treatment update(Integer id, Treatment treatment);

    void delete(Integer id);

}

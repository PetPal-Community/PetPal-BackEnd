package com.ingsw.petpal.service;


import com.ingsw.petpal.model.entity.Carer;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CarerService {

    List<Carer> findAll();
    Carer create(Carer carer);
    Carer findById(int id);
    Carer update(Carer carer);

    @Transactional
    Carer update(Integer id, Carer updatedCarer);

    void delete(Integer id);
}

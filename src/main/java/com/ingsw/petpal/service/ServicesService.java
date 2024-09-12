package com.ingsw.petpal.service;


import com.ingsw.petpal.model.entity.Services;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ServicesService {

    List<Services> findAll();
    Services create(Services medicDocuments);
    Services findById(int id);
    Services update(Services medicDocuments);

    @Transactional
    Services update(Integer id, Services updateServices);

    void delete(Integer id);
}


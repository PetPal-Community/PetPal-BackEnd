package com.ingsw.petpal.service;

import com.ingsw.petpal.model.entity.Contrats;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ContratsService {
    List<Contrats> findAll();


    Contrats create(Contrats contrats);
    Contrats findById(int id);
    Contrats update(Contrats contrats);

    @Transactional
    Contrats update(Integer id, Contrats contrats);

    void delete(Integer id);

}

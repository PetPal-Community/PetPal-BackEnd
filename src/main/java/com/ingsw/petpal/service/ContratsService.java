package com.ingsw.petpal.service;

import com.ingsw.petpal.dto.ContratoDetailsDTO;
import com.ingsw.petpal.dto.ContratsCreateUpdateDTO;
import com.ingsw.petpal.model.entity.Contrats;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ContratsService {
    List<ContratoDetailsDTO> findAll();


    ContratoDetailsDTO create(ContratsCreateUpdateDTO contrats);
    ContratoDetailsDTO findById(int id);
    ContratoDetailsDTO update(ContratsCreateUpdateDTO contrats);

    @Transactional
    ContratoDetailsDTO update(Integer id, ContratsCreateUpdateDTO contrats);

    void delete(Integer id);

}

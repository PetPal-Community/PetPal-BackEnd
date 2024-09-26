package com.ingsw.petpal.service;

import com.ingsw.petpal.dto.MensajesCreateUpdateDTO;
import com.ingsw.petpal.model.entity.Mensajes;
import com.ingsw.petpal.dto.MensajeDetailsDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MensajesService {
    List<MensajeDetailsDTO> findAll();


    MensajeDetailsDTO create(MensajesCreateUpdateDTO mensajes);
    MensajeDetailsDTO findById(int id);
    MensajeDetailsDTO update(MensajesCreateUpdateDTO mensajes);

    @Transactional
    MensajeDetailsDTO update(Integer id, MensajesCreateUpdateDTO mensajes);

    void delete(Integer id);

}

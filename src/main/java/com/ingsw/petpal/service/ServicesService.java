package com.ingsw.petpal.service;

import com.ingsw.petpal.dto.ReviewDTO;
import com.ingsw.petpal.dto.ReviewDetailsDTO;
import com.ingsw.petpal.dto.ServicesDTO;
import com.ingsw.petpal.dto.ServicesDetailsDTO;
import com.ingsw.petpal.model.entity.Services;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ServicesService {

    List<ServicesDetailsDTO> getAll();

    List<ServicesDetailsDTO> getServicesporCuidador(Integer carerId);

    ServicesDetailsDTO findById(Integer id);

    ServicesDetailsDTO create(ServicesDTO service);

    ServicesDetailsDTO update(Integer id, ServicesDTO updatedservice);

    void delete(Integer id);
}
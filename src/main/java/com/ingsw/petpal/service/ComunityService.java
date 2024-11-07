package com.ingsw.petpal.service;

import com.ingsw.petpal.dto.CommunityDTO;
import com.ingsw.petpal.dto.CommunityDetailsDTO;
import com.ingsw.petpal.model.entity.Community;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ComunityService {
    List<CommunityDetailsDTO> getAll();

    CommunityDetailsDTO findById(Integer id);

    CommunityDetailsDTO create(CommunityDTO comunidadDTO);

    CommunityDetailsDTO update(Integer id, CommunityDTO updatedComunidadDTO);

    void delete(Integer id);


}

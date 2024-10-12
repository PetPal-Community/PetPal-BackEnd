package com.ingsw.petpal.service;

import com.ingsw.petpal.dto.CommunityDTO;
import com.ingsw.petpal.model.entity.Community;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ComunityService {
    List<CommunityDTO> getAll();

    CommunityDTO findById(Integer id);

    CommunityDTO create(CommunityDTO comunidadDTO);

    CommunityDTO update(Integer id, CommunityDTO updatedComunidadDTO);

    void delete(Integer id);


}

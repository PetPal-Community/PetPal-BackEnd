package com.ingsw.petpal.service;


import com.ingsw.petpal.model.entity.Community;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ComunityService {
    List<Community> findAll();
    Community create(Community community);
    Community findById(int id);
    Community update(Community community);

    @Transactional
    Community update(Integer id, Community updateCommunity);

    void delete(Integer id);


}

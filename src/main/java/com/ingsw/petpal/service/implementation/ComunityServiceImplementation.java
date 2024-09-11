package com.ingsw.petpal.service.implementation;


import com.ingsw.petpal.model.entity.Community;
import com.ingsw.petpal.repository.ComunityyRepository;
import com.ingsw.petpal.service.ComunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ComunityServiceImplementation implements ComunityService {

    private final ComunityyRepository comunityyRepository;
    @Transactional(readOnly = true)
    @Override
    public List<Community> findAll() {
        return comunityyRepository.findAll();
    }

    @Transactional()
    @Override
    public Community create(Community community) {
        return comunityyRepository.save(community);
    }

    @Transactional(readOnly = true)
    @Override
    public Community findById(int id) {
        return comunityyRepository.findById(id).orElseThrow(()-> new RuntimeException("Comunity not found"));
    }

    @Override
    public Community update(Community community) {
        return null;
    }


    @Transactional
    @Override
    public Community update(Integer id, Community updateCommunity) {

        Community comunityFromDb = findById(id);
        comunityFromDb.setNombre(updateCommunity.getNombre());
        comunityFromDb.setDescripcion(updateCommunity.getDescripcion());
        return comunityyRepository.save(comunityFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Community comunityFromDb = findById(id);
        comunityyRepository.delete(comunityFromDb);
    }


}

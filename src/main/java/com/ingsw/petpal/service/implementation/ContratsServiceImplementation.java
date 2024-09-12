package com.ingsw.petpal.service.implementation;


import com.ingsw.petpal.model.entity.Contrats;
import com.ingsw.petpal.repository.ContratsRepository;
import com.ingsw.petpal.service.ContratsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ContratsServiceImplementation implements ContratsService {

    private final ContratsRepository contratsRepository;


    @Transactional(readOnly = true)
    @Override
    public List<Contrats> findAll() {
        return contratsRepository.findAll();
    }

    @Transactional
    @Override
    public Contrats create(Contrats contrats) {
        return contratsRepository.save(contrats);
    }

    @Transactional(readOnly = true)
    @Override
    public Contrats findById(int id) {
        return contratsRepository.findById(id).orElseThrow(()->new RuntimeException("Contrats not found" + id));
    }


    @Override
    public Contrats update(Contrats contrats) {
        return null;
    }

    @Transactional
    @Override
    public Contrats update(Integer id, Contrats updatedContrats) {
        Contrats contratsFromDb = findById(id);

        contratsFromDb.setFechaContratacion(updatedContrats.getFechaContratacion());
        contratsFromDb.setEstado(updatedContrats.getEstado());
        contratsFromDb.setDetalles(updatedContrats.getDetalles());

        return contratsRepository.save(contratsFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Contrats medicFromDb = findById(id);
        contratsRepository.delete(medicFromDb);
    }
}

package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.exception.ResourceNotFoundException;
import com.ingsw.petpal.model.entity.Carer;
import com.ingsw.petpal.model.entity.MedicDocuments;
import com.ingsw.petpal.repository.CarerRepository;
import com.ingsw.petpal.service.CarerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service

public class CarerServiceImpl implements CarerService {

    private final CarerRepository carerRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Carer> findAll() {
        return carerRepository.findAll();
    }

    @Transactional
    @Override
    public Carer create(Carer carer) {
        return carerRepository.save(carer);
    }

    @Transactional(readOnly = true)
    @Override
    public Carer findById(int id) {
        return carerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("not found" + id));
    }


    @Override
    public Carer update(Carer carer) {
        return null;
    }

    @Transactional
    @Override
    public Carer update(Integer id, Carer updateCarer) {
        Carer carerFromDb = findById(id);

        carerFromDb.setNombre(updateCarer.getNombre());
        carerFromDb.setApellido(updateCarer.getApellido());
        //carerFromDb.setEmail(updateCarer.getEmail());
        carerFromDb.setTelefono(updateCarer.getTelefono());
        //carerFromDb.setContrasenia(updateCarer.getContrasenia());

        return carerRepository.save(carerFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Carer carerFromdb = findById(id);
        carerRepository.delete(carerFromdb);
    }
}

package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.exception.ResourceNotFoundException;
import com.ingsw.petpal.model.entity.MedicDocuments;
import com.ingsw.petpal.model.entity.Services;
import com.ingsw.petpal.repository.ServicesRepository;
import com.ingsw.petpal.service.ServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ServicesServiceImpl implements ServicesService {

    private final ServicesRepository servicesRepository;


    @Transactional(readOnly = true)
    @Override
    public List<Services> findAll() {
        return servicesRepository.findAll();
    }

    @Transactional
    @Override
    public Services create(Services services) {
        return servicesRepository.save(services);
    }

    @Transactional(readOnly = true)
    @Override
    public Services findById(int id) {
        return servicesRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("not found" + id));
    }


    @Override
    public Services update(Services services) {
        return null;
    }

    @Transactional
    @Override
    public Services update(Integer id, Services services) {
        Services servicesFromDb = findById(id);

        servicesFromDb.setTipo_servicio(services.getTipo_servicio());
        servicesFromDb.setDescripcion(services.getDescripcion());
        servicesFromDb.setPrecio(services.getPrecio());

        return servicesRepository.save(servicesFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Services servicesFromDb = findById(id);
        servicesRepository.delete(servicesFromDb);
    }
}

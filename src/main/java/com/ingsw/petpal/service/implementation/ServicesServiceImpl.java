package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.dto.ReviewDTO;
import com.ingsw.petpal.dto.ReviewDetailsDTO;
import com.ingsw.petpal.dto.ServicesDTO;
import com.ingsw.petpal.dto.ServicesDetailsDTO;
import com.ingsw.petpal.exception.ResourceNotFoundException;
import com.ingsw.petpal.mapper.ReviewMapper;
import com.ingsw.petpal.mapper.ServicesMapper;
import com.ingsw.petpal.model.entity.*;
import com.ingsw.petpal.repository.CarerRepository;
import com.ingsw.petpal.repository.ReviewRepository;
import com.ingsw.petpal.repository.ServicesRepository;
import com.ingsw.petpal.repository.UserRepository;
import com.ingsw.petpal.service.ServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ServicesServiceImpl implements ServicesService {

    private final ServicesRepository servicesRepository;
    private final ServicesMapper servicesMapper;
    private final CarerRepository carerRepository;


    @Transactional(readOnly = true)
    @Override
    public List<ServicesDetailsDTO> getAll() {
        List<Services> services = servicesRepository.findAll();
        return services.stream().map(servicesMapper::toDetailsDTO).toList();
    }


    @Transactional(readOnly = true)
    @Override
    public ServicesDetailsDTO findById(Integer id) {
        Services service = servicesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrada con id: " + id));
        return servicesMapper.toDetailsDTO(service);

    }

    @Transactional
    @Override
    public ServicesDetailsDTO create(ServicesDTO servicesDTO) {
        Carer carer = carerRepository.findById(servicesDTO.getCuidador()).orElseThrow(() -> new ResourceNotFoundException("Cuidador no encontrado con id: " + servicesDTO.getCuidador()));
        Services services = servicesMapper.toEntity(servicesDTO);
        services.setCuidador(carer);
        return servicesMapper.toDetailsDTO(servicesRepository.save(services));
    }

    @Transactional
    @Override
    public ServicesDetailsDTO update(Integer id, ServicesDTO updatedServicesDTO) {
        Services servicesfromdb = servicesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado con id " + id));

        Carer carer = carerRepository.findById(updatedServicesDTO.getCuidador()).orElseThrow(() -> new ResourceNotFoundException("Cuidador no encontrado con id: " + updatedServicesDTO.getCuidador()));
        servicesfromdb.setCuidador(carer);
        servicesfromdb.setTipo_servicio(updatedServicesDTO.getTipo_servicio());
        servicesfromdb.setDescripcion(updatedServicesDTO.getDescripcion());
        servicesfromdb.setPrecio(updatedServicesDTO.getPrecio());
        return servicesMapper.toDetailsDTO(servicesRepository.save(servicesfromdb));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Services servicesfromdb = servicesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado con id " + id));
        servicesRepository.delete(servicesfromdb);
    }
}

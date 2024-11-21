package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.dto.ReviewDTO;
import com.ingsw.petpal.dto.ReviewDetailsDTO;
import com.ingsw.petpal.dto.ServicesDTO;
import com.ingsw.petpal.dto.ServicesDetailsDTO;
import com.ingsw.petpal.exception.ResourceNotFoundException;
import com.ingsw.petpal.mapper.ReviewMapper;
import com.ingsw.petpal.mapper.ServicesMapper;
import com.ingsw.petpal.model.entity.*;
import com.ingsw.petpal.repository.*;
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
    private final UserGeneralRepository userGeneralRepository;


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
        UserGeneral userGCarer = userGeneralRepository.findById(servicesDTO.getCuidadorGId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + servicesDTO.getCuidadorGId()));
        Integer userIDCarer = userGCarer.getCarer().getId();

        Carer carer = carerRepository.findById(userIDCarer).orElseThrow(() -> new ResourceNotFoundException("carer not found with id: " + userIDCarer));

        Services services = servicesMapper.toEntity(servicesDTO);
        services.setCuidador(carer);
        return servicesMapper.toDetailsDTO(servicesRepository.save(services));
    }

    @Transactional
    @Override
    public ServicesDetailsDTO update(Integer id, ServicesDTO updatedServicesDTO) {
        Services servicesfromdb = servicesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado con id " + id));

        UserGeneral userGCarer = userGeneralRepository.findById(updatedServicesDTO.getCuidadorGId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + updatedServicesDTO.getCuidadorGId()));
        Integer userIDCarer = userGCarer.getCarer().getId();

        Carer carer = carerRepository.findById(userIDCarer).orElseThrow(() -> new ResourceNotFoundException("carer not found with id: " + userIDCarer));

        servicesfromdb.setCuidador(carer);
        servicesfromdb.setTipo_servicio(updatedServicesDTO.getTipo_servicio());
        servicesfromdb.setDescripcion(updatedServicesDTO.getDescripcion());
        servicesfromdb.setPrecio(updatedServicesDTO.getPrecio());
        servicesfromdb.setPicRuta(updatedServicesDTO.getPicRuta());
        return servicesMapper.toDetailsDTO(servicesRepository.save(servicesfromdb));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Services servicesfromdb = servicesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado con id " + id));
        servicesRepository.delete(servicesfromdb);
    }


    @Transactional
    @Override
    public List<ServicesDetailsDTO> getServicesporCuidador(Integer carerId){

        UserGeneral userGCarer = userGeneralRepository.findById(carerId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + carerId));
        Integer userIDCarer = userGCarer.getCarer().getId();

        Carer carer = carerRepository.findById(userIDCarer).orElseThrow(() -> new ResourceNotFoundException("carer not found with id: " + userIDCarer));
        List<Services> services = servicesRepository.findByCuidador(carer);
        return services.stream()
                .map(servicesMapper::toDetailsDTO)
                .toList();
    }
}

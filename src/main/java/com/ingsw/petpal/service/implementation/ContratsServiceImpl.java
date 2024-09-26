package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.dto.ContratoDetailsDTO;
import com.ingsw.petpal.dto.ContratsCreateUpdateDTO;
import com.ingsw.petpal.exception.ResourceNotFoundException;
import com.ingsw.petpal.mapper.ContratsMapper;

import com.ingsw.petpal.model.entity.*;
import com.ingsw.petpal.repository.CarerRepository;
import com.ingsw.petpal.repository.ContratsRepository;
import com.ingsw.petpal.repository.ServicesRepository;
import com.ingsw.petpal.repository.UserRepository;
import com.ingsw.petpal.service.ContratsService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ContratsServiceImpl implements ContratsService {

    private final ContratsRepository contratsRepository;
    private final ServicesRepository servicesRepository;
    private final CarerRepository carerRepository;
    private final UserRepository userRepository;
    private final ContratsMapper contratsMapper;


    @Transactional(readOnly = true)
    @Override
    public List<ContratoDetailsDTO> findAll() {
        List<Contrats> contrats = contratsRepository.findAll();
        return contrats.stream()
                .map(contratsMapper::toDetailsDTO)
                .toList();
    }

    @Transactional
    @Override
    public ContratoDetailsDTO create(ContratsCreateUpdateDTO contratsCreateUpdateDTO) {
        Services services = servicesRepository.findById(contratsCreateUpdateDTO.getServicio()).orElseThrow(() ->new ResourceNotFoundException("services not found with id: " + contratsCreateUpdateDTO.getServicio()));
        Carer carer = carerRepository.findById(contratsCreateUpdateDTO.getCuidador()).orElseThrow(() -> new ResourceNotFoundException("carer not found with id: " + contratsCreateUpdateDTO.getCuidador()));
        User user = userRepository.findById(contratsCreateUpdateDTO.getUsuario()).orElseThrow(() -> new ResourceNotFoundException("user not found with id: " + contratsCreateUpdateDTO.getUsuario()));
        Contrats contrats =  contratsMapper.toEntity(contratsCreateUpdateDTO);

        contrats.setServicio(services);
        contrats.setCuidador(carer);
        contrats.setUsuario(user);
        return contratsMapper.toDetailsDTO(contratsRepository.save(contrats));
    }

    @Transactional(readOnly = true)
    @Override
    public ContratoDetailsDTO findById(int id) {
        Contrats contrats = contratsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("contrat not found with id: " + id));
        return contratsMapper.toDetailsDTO(contrats);
    }


    @Override
    public ContratoDetailsDTO update(ContratsCreateUpdateDTO contrats) {
        return null;
    }

    @Transactional
    @Override
    public ContratoDetailsDTO update(Integer id, ContratsCreateUpdateDTO updatedContrats) {
        Contrats contratsFromDb =contratsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("contrat not found with id: " + id));

        Services services = servicesRepository.findById(updatedContrats.getServicio()).orElseThrow(() ->new ResourceNotFoundException("services not found with id: " + updatedContrats.getServicio()));
        Carer carer = carerRepository.findById(updatedContrats.getCuidador()).orElseThrow(() -> new ResourceNotFoundException("carer not found with id: " + updatedContrats.getCuidador()));
        User user = userRepository.findById(updatedContrats.getUsuario()).orElseThrow(() -> new ResourceNotFoundException("user not found with id: " + updatedContrats.getUsuario()));


        contratsFromDb.setServicio(services);
        contratsFromDb.setCuidador(carer);
        contratsFromDb.setUsuario(user);
        contratsFromDb.setFechaContratacion(updatedContrats.getFechaContratacion());
        contratsFromDb.setEstado(updatedContrats.getEstado());
        contratsFromDb.setDetalles(updatedContrats.getDetalles());

        return contratsMapper.toDetailsDTO(contratsRepository.save(contratsFromDb));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Contrats contrats = contratsRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("contrat not found with id: " + id));
        contratsRepository.delete(contrats);
    }
}

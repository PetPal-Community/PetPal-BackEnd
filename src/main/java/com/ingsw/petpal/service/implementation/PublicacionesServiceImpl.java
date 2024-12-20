package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.dto.PublicacionCreateDTO;
import com.ingsw.petpal.dto.PublicacionDetailsDTO;
import com.ingsw.petpal.exception.ResourceNotFoundException;

import com.ingsw.petpal.mapper.PublicacionMapper;
import com.ingsw.petpal.model.entity.Community;
import com.ingsw.petpal.model.entity.Publicaciones;

import com.ingsw.petpal.model.entity.User;
import com.ingsw.petpal.model.entity.UserGeneral;
import com.ingsw.petpal.repository.ComunityRepository;
import com.ingsw.petpal.repository.PublicacionesRepository;
import com.ingsw.petpal.repository.UserGeneralRepository;
import com.ingsw.petpal.repository.UserRepository;
import com.ingsw.petpal.service.PublicacionesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PublicacionesServiceImpl implements PublicacionesService {
    private final PublicacionesRepository publicacionesRepository;
    private final UserRepository userRepository;
    private final PublicacionMapper publicacionMapper;
    private final ComunityRepository comunityRepository;
    private final UserGeneralRepository userGeneralRepository;


    @Transactional(readOnly = true)
    @Override
    public List<PublicacionDetailsDTO> findAll() {
        List<Publicaciones> publicaciones = publicacionesRepository.findAll();
        return publicaciones.stream()
                .map(publicacionMapper::toDetailsDTO)
                .toList();
    }

    @Transactional
    @Override
    public PublicacionDetailsDTO create(PublicacionCreateDTO publicacionesDTO) {
        Publicaciones publicacion = publicacionMapper.toEntity(publicacionesDTO);

        UserGeneral userG = userGeneralRepository.findById(publicacionesDTO.getUsuarioGId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + publicacionesDTO.getUsuarioGId()));
        Integer userID = userG.getUsuario().getId();

        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userID));


        if (publicacionesDTO.getComunidad() != null) {
            Community comunidad = comunityRepository.findById(publicacionesDTO.getComunidad()).orElseThrow(()-> new ResourceNotFoundException("Comunidad no encontrado " + publicacionesDTO.getComunidad()));
            publicacion.setComunidad(comunidad);
        }
        publicacion.setUsuario(user);
        publicacion.setFechaPublicacion(LocalDateTime.now());

        return publicacionMapper.toDetailsDTO(publicacionesRepository.save(publicacion));
    }

    @Transactional(readOnly = true)
    @Override
    public PublicacionDetailsDTO findById(Integer id) {
       Publicaciones publicacion = publicacionesRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("publicacion no encontrada con el id: " + id));
       return publicacionMapper.toDetailsDTO(publicacion);
    }


    @Transactional
    @Override
    public PublicacionDetailsDTO update(Integer id, PublicacionCreateDTO updatePublicaciones) {
        UserGeneral userG = userGeneralRepository.findById(updatePublicaciones.getUsuarioGId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + updatePublicaciones.getUsuarioGId()));
        Integer userID = userG.getUsuario().getId();

        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userID));
        Publicaciones publicacionFromDB = publicacionesRepository.findById(updatePublicaciones.getId()).orElseThrow(()-> new ResourceNotFoundException("publicacion no encontrada con el id: " + id));
        if (updatePublicaciones.getComunidad() != null) {
            Community comunidad = comunityRepository.findById(updatePublicaciones.getComunidad()).orElseThrow(()-> new ResourceNotFoundException("Comunidad no encontrado " + updatePublicaciones.getComunidad()));
            publicacionFromDB.setComunidad(comunidad);
        }
        publicacionFromDB.setUsuario(user);
        publicacionFromDB.setContenido(updatePublicaciones.getContenido());
        publicacionFromDB.setFechaActualización(LocalDateTime.now());
        publicacionFromDB.setPicRuta(updatePublicaciones.getPicRuta());

        return publicacionMapper.toDetailsDTO(publicacionesRepository.save(publicacionFromDB));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Publicaciones publicacionesFromDb = publicacionesRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("publicacion no encontrado con el id: " + id));
        publicacionesRepository.delete(publicacionesFromDb);
    }
}

package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.dto.ComentsCreateUpdateDTO;
import com.ingsw.petpal.dto.ComentsDetailsDTO;

import com.ingsw.petpal.dto.TreatmentCreateUpdateDTO;
import com.ingsw.petpal.dto.TreatmentDetailsDTO;
import com.ingsw.petpal.exception.ResourceNotFoundException;
import com.ingsw.petpal.mapper.CommentsMapper;
import com.ingsw.petpal.model.entity.*;

import com.ingsw.petpal.repository.PublicacionesRepository;
import com.ingsw.petpal.repository.UserGeneralRepository;
import com.ingsw.petpal.repository.UserRepository;
import com.ingsw.petpal.repository.comentsRepository;
import com.ingsw.petpal.service.ComentsService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ComentsServiceImpl implements ComentsService {
    private final UserRepository userRepository;
    private final PublicacionesRepository publicacionesRepository;
    private final comentsRepository cOmentsRepository;
    private final CommentsMapper comentsMapper;
    private final UserGeneralRepository userGeneralRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ComentsDetailsDTO> findAll() {
        List<coments> comentarios = cOmentsRepository.findAll();
        return comentarios.stream()
                .map(comentsMapper::toDetailsDTO)
                .toList();
    }

    @Transactional
    @Override
    public ComentsDetailsDTO create(ComentsCreateUpdateDTO comentsCreateUpdateDTO) {

        UserGeneral userG = userGeneralRepository.findById(comentsCreateUpdateDTO.getUsuarioGId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + comentsCreateUpdateDTO.getUsuarioGId()));
        Integer userID = userG.getUsuario().getId();

        User usuario = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userID));


        Publicaciones publicacion = publicacionesRepository.findById(comentsCreateUpdateDTO.getPublicacion()).orElseThrow(()-> new ResourceNotFoundException("Publicacion not found with id: " + comentsCreateUpdateDTO.getPublicacion()));
        coments comentarios = comentsMapper.toEntity(comentsCreateUpdateDTO);
        comentarios.setPublicacion(publicacion);
        comentarios.setUsuario(usuario);
        comentarios.setFechacreacion(LocalDateTime.now());
        return comentsMapper.toDetailsDTO(cOmentsRepository.save(comentarios));
    }

    @Transactional(readOnly = true)
    @Override
    public ComentsDetailsDTO findById(Integer id){
        coments comentario = cOmentsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("comentario not found with id: " + id));
        return comentsMapper.toDetailsDTO(comentario);
    }

    @Transactional
    @Override
    public ComentsDetailsDTO update(Integer id, ComentsCreateUpdateDTO comentsCreateUpdateDTO) {
        coments comentFromDb = cOmentsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("comentario not found with id: " + id));

        UserGeneral userG = userGeneralRepository.findById(comentsCreateUpdateDTO.getUsuarioGId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + comentsCreateUpdateDTO.getUsuarioGId()));
        Integer userID = userG.getUsuario().getId();

        User usuario = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userID));


        Publicaciones publicacion = publicacionesRepository.findById(comentsCreateUpdateDTO.getPublicacion()).orElseThrow(()-> new ResourceNotFoundException("Publicacion not found with id: " + comentsCreateUpdateDTO.getPublicacion()));

        comentFromDb.setPublicacion(publicacion);
        comentFromDb.setUsuario(usuario);
        comentFromDb.setContenido(comentsCreateUpdateDTO.getContenido());
        comentFromDb.setFechaupdate(LocalDateTime.now());
        return comentsMapper.toDetailsDTO(cOmentsRepository.save(comentFromDb));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        coments comentario = cOmentsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("comentario not found with id: " + id));
        cOmentsRepository.delete(comentario);
    }

}
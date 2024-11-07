package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.dto.MensajeDetailsDTO;
import com.ingsw.petpal.dto.MensajesCreateUpdateDTO;
import com.ingsw.petpal.exception.ResourceNotFoundException;
import com.ingsw.petpal.mapper.MensajesMapper;
import com.ingsw.petpal.model.entity.Carer;
import com.ingsw.petpal.model.entity.Mensajes;

import com.ingsw.petpal.model.entity.User;
import com.ingsw.petpal.model.entity.UserGeneral;
import com.ingsw.petpal.repository.CarerRepository;
import com.ingsw.petpal.repository.MensajesRepository;
import com.ingsw.petpal.repository.UserGeneralRepository;
import com.ingsw.petpal.repository.UserRepository;
import com.ingsw.petpal.service.MensajesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MensajesImplementation implements MensajesService {

    private final MensajesRepository mensajesRepository;
    private final UserRepository userRepository;
    private final CarerRepository carerRepository;
    private final MensajesMapper mensajesMapper;
    private final UserGeneralRepository userGeneralRepository;


    @Transactional(readOnly = true)
    @Override
    public List<MensajeDetailsDTO> findAll() {
        List<Mensajes> mensajes = mensajesRepository.findAll();
        return mensajes.stream()
                .map(mensajesMapper::toDetailsDTO)
                .toList();
    }


    @Transactional
    @Override
    public MensajeDetailsDTO create(MensajesCreateUpdateDTO mensajesCreateUpdateDTO) {
        UserGeneral userGCarer = userGeneralRepository.findById(mensajesCreateUpdateDTO.getCuidadorGId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + mensajesCreateUpdateDTO.getCuidadorGId()));
        Integer userIDCarer = userGCarer.getCarer().getId();

        Carer carer = carerRepository.findById(userIDCarer).orElseThrow(() -> new ResourceNotFoundException("carer not found with id: " + userIDCarer));


        UserGeneral userG = userGeneralRepository.findById(mensajesCreateUpdateDTO.getUsuarioGId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + mensajesCreateUpdateDTO.getUsuarioGId()));
        Integer userID = userG.getUsuario().getId();

        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userID));
        Mensajes mensajes = mensajesMapper.toEntity(mensajesCreateUpdateDTO);
        mensajes.setFechaEnvio(LocalDateTime.from(LocalDateTime.now()));
        mensajes.setUsuario(user);
        mensajes.setCuidador(carer);
        return mensajesMapper.toDetailsDTO(mensajesRepository.save(mensajes));
    }


    @Transactional(readOnly = true)
    @Override
    public MensajeDetailsDTO findById(int id) {
        Mensajes mensajes = mensajesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Mensaje found with id: " + id));
        return mensajesMapper.toDetailsDTO(mensajes);

    }

    @Override
    public MensajeDetailsDTO update(MensajesCreateUpdateDTO mensaje) {
        return null;
    }


    @Transactional
    @Override
    public MensajeDetailsDTO update(Integer id, MensajesCreateUpdateDTO updatedMensajes) {
        Mensajes mensajesFromDb = mensajesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Mensaje found with id: " + id));

        UserGeneral userGCarer = userGeneralRepository.findById(updatedMensajes.getCuidadorGId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + updatedMensajes.getCuidadorGId()));
        Integer userIDCarer = userGCarer.getCarer().getId();

        Carer carer = carerRepository.findById(userIDCarer).orElseThrow(() -> new ResourceNotFoundException("carer not found with id: " + userIDCarer));


        UserGeneral userG = userGeneralRepository.findById(updatedMensajes.getUsuarioGId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + updatedMensajes.getUsuarioGId()));
        Integer userID = userG.getUsuario().getId();

        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userID));

        mensajesFromDb.setCuidador(carer);
        mensajesFromDb.setUsuario(user);
        mensajesFromDb.setContenido(updatedMensajes.getContenido());
        mensajesFromDb.setFechaUpdate(LocalDateTime.from(LocalDateTime.now()));

        return mensajesMapper.toDetailsDTO(mensajesRepository.save(mensajesFromDb));
    }


    @Transactional
    @Override
    public void delete(Integer id) {
        Mensajes mensajes = mensajesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Mensaje found with id: " + id));
        mensajesRepository.delete(mensajes);
    }
}

package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.dto.CommunityDTO;
import com.ingsw.petpal.exception.DuplicateCommunityException;
import com.ingsw.petpal.exception.ResourceNotFoundException;
import com.ingsw.petpal.mapper.ComunidadMapper;
import com.ingsw.petpal.model.entity.Community;
import com.ingsw.petpal.model.entity.User;
import com.ingsw.petpal.repository.ComunityRepository;
import com.ingsw.petpal.repository.UserRepository;
import com.ingsw.petpal.service.ComunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComunityServiceImpl implements ComunityService {

    private final ComunityRepository comunidadRepository;
    private final ComunidadMapper comunidadMapper;

    private final UserRepository usuarioRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CommunityDTO> getAll() {
        List<Community> comunidades = comunidadRepository.findAll();
        return comunidades.stream().map(comunidadMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public CommunityDTO findById(Integer id) {
        Community comunidad = comunidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comunidad no encontrada"));
        return comunidadMapper.toDTO(comunidad);
    }

    @Transactional
    @Override
    public CommunityDTO create(CommunityDTO comunidadDTO) {
        // Verificar si ya existe una comunidad con el mismo nombre
        comunidadRepository.findByNombre(comunidadDTO.getNombre())
                .ifPresent(comunidad -> {
                    throw new DuplicateCommunityException("Ya existe una comunidad con el mismo nombre");
                });

        //add new

        /*
        // Verificar si el creador (usuario) existe
        User creador = usuarioRepository.findById(comunidadDTO.getCreadorId())
                .orElseThrow(() -> new ResourceNotFoundException("El creador no existe"));

        // Mapear DTO a entidad
        Community comunidad = comunidadMapper.toEntity(comunidadDTO);


        // Establecer la relación con el creador (usuario)
        comunidad.setCreador(creador);

        // Intentar guardar la nueva comunidad
        comunidad = comunidadRepository.save(comunidad);

        return comunidadMapper.toDTO(comunidad);

         */

        // Verificar si el creador (usuario) existe
        User creador = usuarioRepository.findById(comunidadDTO.getCreadorId())
                .orElseThrow(() -> new ResourceNotFoundException("El creador no existe"));

        // Mapear DTO a entidad

        Community comunidad = comunidadMapper.toEntity(comunidadDTO);
        // Asignar solo el nombre del creador a la comunidad
        comunidad.setNombre(creador.getNombre());
        comunidad.setCreadorId(creador.getId());
        comunidad.setCreador(creador);
        // Guardar la nueva comunidad y convertir a DTO
        return comunidadMapper.toDTO(comunidadRepository.save(comunidad));

    }

    @Transactional
    @Override
    public CommunityDTO update(Integer id, CommunityDTO updatedComunidadDTO) {
        Community comunidadFromDb = comunidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comunidad no encontrada"));

        comunidadFromDb.setNombre(updatedComunidadDTO.getNombre());
        comunidadFromDb.setDescripcion(updatedComunidadDTO.getDescripcion());

        comunidadFromDb = comunidadRepository.save(comunidadFromDb);
        return comunidadMapper.toDTO(comunidadFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Community comunidadFromDb = comunidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comunidad no encontrada"));
        comunidadRepository.delete(comunidadFromDb);
    }
}
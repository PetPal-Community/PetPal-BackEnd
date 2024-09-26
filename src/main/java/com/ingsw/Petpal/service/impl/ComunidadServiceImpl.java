package com.ingsw.Petpal.service.impl;

import com.ingsw.Petpal.dto.ComunidadDTO;
import com.ingsw.Petpal.exception.BadRequestException;
import com.ingsw.Petpal.exception.ResourceNotFoundException;
import com.ingsw.Petpal.exception.DuplicateCommunityException;
import com.ingsw.Petpal.mapper.ComunidadMapper;
import com.ingsw.Petpal.model.entity.Comunidad;
import com.ingsw.Petpal.repository.ComunidadRepository;
import com.ingsw.Petpal.service.ComunidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComunidadServiceImpl implements ComunidadService {

    private final ComunidadRepository comunidadRepository;
    private final ComunidadMapper comunidadMapper;

    @Transactional(readOnly = true)
    @Override
    public List<ComunidadDTO> getAll() {
        List<Comunidad> comunidades = comunidadRepository.findAll();
        return comunidades.stream().map(comunidadMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public ComunidadDTO findById(Integer id) {
        Comunidad comunidad = comunidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comunidad no encontrada"));
        return comunidadMapper.toDTO(comunidad);
    }

    @Transactional
    @Override
    public ComunidadDTO create(ComunidadDTO comunidadDTO) {
        // Verificar si ya existe una comunidad con el mismo nombre
        comunidadRepository.findByNombre(comunidadDTO.getNombre())
                .ifPresent(comunidad -> {
                    throw new DuplicateCommunityException("Ya existe una comunidad con el mismo nombre");
                });

        // Mapear DTO a entidad
        Comunidad comunidad = comunidadMapper.toEntity(comunidadDTO);

        // Intentar guardar la nueva comunidad
        comunidad = comunidadRepository.save(comunidad);

        return comunidadMapper.toDTO(comunidad);
    }

    @Transactional
    @Override
    public ComunidadDTO update(Integer id, ComunidadDTO updatedComunidadDTO) {
        Comunidad comunidadFromDb = comunidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comunidad no encontrada"));

        comunidadFromDb.setNombre(updatedComunidadDTO.getNombre());
        comunidadFromDb.setDescripcion(updatedComunidadDTO.getDescripcion());

        comunidadFromDb = comunidadRepository.save(comunidadFromDb);
        return comunidadMapper.toDTO(comunidadFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Comunidad comunidadFromDb = comunidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comunidad no encontrada"));
        comunidadRepository.delete(comunidadFromDb);
    }
}

package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.dto.CarerDTO;
import com.ingsw.petpal.exception.BadRequestException;
import com.ingsw.petpal.exception.ResourceNotFoundException;
import com.ingsw.petpal.mapper.CarerMapper;
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
    private final CarerMapper carerMapper;

    @Transactional(readOnly = true)
    @Override
    public List<CarerDTO> getAll() {
        List<Carer> cuidadores = carerRepository.findAll();
        return cuidadores.stream()
                .map(carerMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public CarerDTO findById(Integer id) {
        Carer carer = carerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Carer no encontrado"));
        return carerMapper.toDTO(carer);
    }

    @Transactional
    @Override
    public CarerDTO create(CarerDTO carerDTO) {
        carerRepository.findByNombreAndApellido(carerDTO.getNombre(), carerDTO.getApellido())
                .ifPresent(carer -> {
                    throw new BadRequestException("El Cuidador ya existe con el mismo nombre y apellido");
                });

        Carer carer = carerMapper.toEntity(carerDTO);
        carer = carerRepository.save(carer);
        return carerMapper.toDTO(carer);
    }

    @Transactional
    @Override
    public CarerDTO update(Integer id, CarerDTO updatedCarerDTO) {
        Carer carerfromDb = carerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuidador no encontrado"));

        carerRepository.findByNombreAndApellido(updatedCarerDTO.getNombre(), updatedCarerDTO.getApellido())
                .filter(existingCarer -> !existingCarer.getId().equals(id))
                .ifPresent(existingCarer -> {throw new BadRequestException("El cuidador ya existe con el mismo nombre y apellido paterno");});

        carerfromDb.setNombre(updatedCarerDTO.getNombre());
        carerfromDb.setApellido(updatedCarerDTO.getApellido());
        carerfromDb.setEmail(updatedCarerDTO.getEmail());
        carerfromDb.setTelefono(updatedCarerDTO.getTelefono());
        carerfromDb.setContrasenia(updatedCarerDTO.getContrasenia());

        carerfromDb = carerRepository.save(carerfromDb);

        return carerMapper.toDTO(carerfromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Carer carerFromDb = carerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cauidador no encontrado"));
        carerRepository.delete(carerFromDb);
    }

}

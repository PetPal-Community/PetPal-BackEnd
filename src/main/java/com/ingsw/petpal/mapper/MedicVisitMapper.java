package com.ingsw.petpal.mapper;

import com.ingsw.petpal.dto.MedicVisitDTO;
import com.ingsw.petpal.model.entity.MedicVisit;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicVisitMapper {

    @Autowired
    private ModelMapper modelMapper;

    // Convertir de entidad a DTO
    public MedicVisitDTO toDTO(MedicVisit medicVisit) {
        return modelMapper.map(medicVisit, MedicVisitDTO.class);
    }

    // Convertir de DTO a entidad
    public MedicVisit toEntity(MedicVisitDTO medicVisitDTO) {
        return modelMapper.map(medicVisitDTO, MedicVisit.class);
    }
}
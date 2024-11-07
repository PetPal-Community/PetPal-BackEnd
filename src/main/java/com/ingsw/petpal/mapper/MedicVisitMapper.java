package com.ingsw.petpal.mapper;

import com.ingsw.petpal.dto.MedicVisitCreateUpdateDTO;
import com.ingsw.petpal.dto.MedicVisitDetailsDTO;
import com.ingsw.petpal.model.entity.MedicVisit;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class MedicVisitMapper {
    private final ModelMapper modelMapper;

    public MedicVisitMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        // Configura ModelMapper para usar estrategia de coincidencia estricta
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public MedicVisitDetailsDTO toDetailsDTO(MedicVisit medicVisit) {
        MedicVisitDetailsDTO detailsDTO = modelMapper.map(medicVisit, MedicVisitDetailsDTO.class);
        if (medicVisit.getMascota() != null) {
            detailsDTO.setNombreMascota(medicVisit.getMascota().getNombre());
        }
        return detailsDTO;
    }


    public MedicVisit toEntity(MedicVisitCreateUpdateDTO medicVisitCreateUpdateDTO) {
        MedicVisit medicVisit = modelMapper.map(medicVisitCreateUpdateDTO, MedicVisit.class);
        return medicVisit;
    }

    public MedicVisitCreateUpdateDTO toDTO(MedicVisit medicVisit) {
        return modelMapper.map(medicVisit, MedicVisitCreateUpdateDTO.class);
    }
}

package com.ingsw.petpal.mapper;

import com.ingsw.petpal.dto.TreatmentCreateUpdateDTO;
import com.ingsw.petpal.dto.TreatmentDetailsDTO;
import com.ingsw.petpal.model.entity.Treatment;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class TreatmentMapper {
    private final ModelMapper modelMapper;
    public TreatmentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        //configura model mapper para usar estrategia de coindcidencia estrcta
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public TreatmentDetailsDTO toDetailsDTO(Treatment treatment) {
        TreatmentDetailsDTO treatmentDetailsDTO = modelMapper.map(treatment, TreatmentDetailsDTO.class);
        treatmentDetailsDTO.setVisitamedica(treatment.getVisitaMedica().getDiagnostico());
        return treatmentDetailsDTO;
    }

    public Treatment toEntity(TreatmentCreateUpdateDTO treatmentCreateUpdateDTO) {
        return modelMapper.map(treatmentCreateUpdateDTO, Treatment.class);
    }

    public TreatmentCreateUpdateDTO toDTO(Treatment treatment) {
        return modelMapper.map(treatment, TreatmentCreateUpdateDTO.class);
    }
}

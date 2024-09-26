package com.ingsw.petpal.mapper;

import com.ingsw.petpal.dto.MedicDocumentsCreateUpdateDTO;
import com.ingsw.petpal.dto.MedicDocumentsDetailsDTO;
import com.ingsw.petpal.model.entity.MedicDocuments;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class MedicDocumentsMapper {
    private ModelMapper modelMapper;

    public MedicDocumentsMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        //configura model mapper para usar estrategia de coindcidencia estrcta
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public MedicDocumentsDetailsDTO toDetailsDTO(MedicDocuments medicDocuments) {
        MedicDocumentsDetailsDTO medicDocumentsDetailsDTO = modelMapper.map(medicDocuments, MedicDocumentsDetailsDTO.class);
        medicDocumentsDetailsDTO.setVisitaMedicaDescripcion(medicDocuments.getVisitamedica().getDiagnostico());
        medicDocumentsDetailsDTO.setNombreMascota(medicDocuments.getVisitamedica().getMascota().getNombre());
        return medicDocumentsDetailsDTO;
    }

    public MedicDocuments toEntity(MedicDocumentsCreateUpdateDTO medicDocumentsCreateUpdateDTO) {
        return modelMapper.map(medicDocumentsCreateUpdateDTO, MedicDocuments.class);
    }

    public MedicDocumentsCreateUpdateDTO toDTO(MedicDocuments medicDocuments) {
        return modelMapper.map(medicDocuments, MedicDocumentsCreateUpdateDTO.class);
    }
}

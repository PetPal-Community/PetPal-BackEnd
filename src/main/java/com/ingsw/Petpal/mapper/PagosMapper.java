package com.ingsw.Petpal.mapper;


import com.ingsw.Petpal.dto.PagosDTO;
import com.ingsw.Petpal.model.entity.Pagos;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PagosMapper {

    private final ModelMapper modelMapper;

    public PagosMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PagosDTO toDTO(Pagos pagos) {
        return modelMapper.map(pagos, PagosDTO.class);
    }

    public Pagos toEntity(PagosDTO pagosDTO) {
        return modelMapper.map(pagosDTO, Pagos.class);
    }
}

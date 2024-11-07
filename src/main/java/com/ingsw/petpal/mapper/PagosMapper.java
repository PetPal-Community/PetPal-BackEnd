package com.ingsw.petpal.mapper;

import com.ingsw.petpal.dto.PagosDTO;
import com.ingsw.petpal.dto.PagosDetails;
import com.ingsw.petpal.model.entity.Contrats;
import com.ingsw.petpal.model.entity.Pagos;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class PagosMapper {

    private final ModelMapper modelMapper;

    public PagosMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public PagosDetails toDetailsDTO(Pagos pagos) {
        PagosDetails dto = modelMapper.map(pagos, PagosDetails.class);
        dto.setNombreCuidador(pagos.getContrato().getCuidador().getNombre() + ' ' + pagos.getContrato().getCuidador().getApellido());
        dto.setNombreUsuario(pagos.getContrato().getUsuario().getNombre() + ' ' + pagos.getContrato().getUsuario().getApellido());
        dto.setNomreServicio(pagos.getContrato().getServicio().getTipo_servicio());
        return dto;
    }
    
    public PagosDTO toDTO(Pagos pagos) {
        return modelMapper.map(pagos, PagosDTO.class);
    }

    public Pagos toEntity(PagosDTO pagosDTO) {
       return modelMapper.map(pagosDTO, Pagos.class);
    }

}
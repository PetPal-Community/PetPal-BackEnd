package com.ingsw.petpal.mapper;

import com.ingsw.petpal.dto.ContratoDetailsDTO;
import com.ingsw.petpal.dto.ContratsCreateUpdateDTO;
import com.ingsw.petpal.model.entity.Contrats;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class ContratsMapper {
    private final ModelMapper modelMapper;
    public ContratsMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public ContratoDetailsDTO toDetailsDTO(Contrats contrats) {
        ContratoDetailsDTO contratoDetailsDTO = modelMapper.map(contrats, ContratoDetailsDTO.class);
        contratoDetailsDTO.setServicioTipoServicio(contrats.getServicio().getTipo_servicio());
        contratoDetailsDTO.setServicioPrecio(contrats.getServicio().getPrecio());
        contratoDetailsDTO.setCuidadorNombreComplt(contrats.getCuidador().getNombre()+' '+contrats.getCuidador().getApellido());
        contratoDetailsDTO.setUsuarioNombreComplt(contrats.getUsuario().getNombre()+' '+contrats.getUsuario().getApellido());
       return contratoDetailsDTO;
    }

    public Contrats toEntity(ContratsCreateUpdateDTO contratsCreateUpdateDTO) {
        return modelMapper.map(contratsCreateUpdateDTO, Contrats.class);
    }

    public ContratsCreateUpdateDTO toDTO(Contrats contrats ) {
        return modelMapper.map(contrats, ContratsCreateUpdateDTO.class);
    }

}

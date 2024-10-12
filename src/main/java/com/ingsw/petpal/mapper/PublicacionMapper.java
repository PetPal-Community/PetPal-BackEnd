package com.ingsw.petpal.mapper;

import com.ingsw.petpal.dto.PublicacionCreateDTO;
import com.ingsw.petpal.dto.PublicacionDetailsDTO;
import com.ingsw.petpal.model.entity.Publicaciones;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class PublicacionMapper {
    private final ModelMapper modelMapper;
    public PublicacionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public PublicacionDetailsDTO toDetailsDTO (Publicaciones publicacion) {
        PublicacionDetailsDTO dto = modelMapper.map(publicacion, PublicacionDetailsDTO.class);
        dto.setNombreUsuario(publicacion.getUsuario().getNombre()+ ' ' + publicacion.getUsuario().getApellido());
        if(publicacion.getComunidad() != null){
            dto.setTituloComunidad(publicacion.getComunidad().getNombre());
        } else {
            dto.setTituloComunidad("No fue publicado en una comunidad");
        }
        return dto;
    }

    public Publicaciones toEntity(PublicacionCreateDTO dto) {
        return modelMapper.map(dto, Publicaciones.class);
    }

    public PublicacionCreateDTO toDTO (Publicaciones publicacion) {
        return modelMapper.map(publicacion, PublicacionCreateDTO.class);
    }
}

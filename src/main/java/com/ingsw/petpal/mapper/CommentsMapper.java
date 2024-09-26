package com.ingsw.petpal.mapper;

import com.ingsw.petpal.dto.CommentsDTO;
import com.ingsw.petpal.model.entity.Comments; // Usamos el nombre correcto de la entidad
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentsMapper {

    @Autowired
    private ModelMapper modelMapper;

    // Método para convertir Comments a CommentsDTO
    public CommentsDTO toDTO(Comments comments) {
        return modelMapper.map(comments, CommentsDTO.class);
    }

    // Método para convertir CommentsDTO a Comments
    public Comments toEntity(CommentsDTO commentsDTO) {
        return modelMapper.map(commentsDTO, Comments.class);
    }
}

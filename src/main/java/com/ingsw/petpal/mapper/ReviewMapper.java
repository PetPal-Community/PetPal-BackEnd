package com.ingsw.petpal.mapper;

import com.ingsw.petpal.dto.ReviewDTO;
import com.ingsw.petpal.dto.ReviewDetailsDTO;
import com.ingsw.petpal.model.entity.Review;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    private final ModelMapper modelMapper;
    public ReviewMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public ReviewDetailsDTO toDetailsDTO(Review review){
        ReviewDetailsDTO reviewDetailsDTO = modelMapper.map(review, ReviewDetailsDTO.class);
        reviewDetailsDTO.setUsuarioNombre(review.getUsuario().getNombre()+' '+review.getUsuario().getApellido());
        reviewDetailsDTO.setCuidadorNombre(review.getCuidador().getNombre()+' '+review.getCuidador().getApellido());
        return reviewDetailsDTO;
    }

    public Review toEntity(ReviewDTO reviewDTO){
        return modelMapper.map(reviewDTO, Review.class);
    }

    public ReviewDTO toDTO(Review review){
        return modelMapper.map(review, ReviewDTO.class);
    }

}

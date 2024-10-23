package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.dto.MensajeDetailsDTO;
import com.ingsw.petpal.dto.MensajesCreateUpdateDTO;
import com.ingsw.petpal.dto.ReviewDTO;
import com.ingsw.petpal.dto.ReviewDetailsDTO;
import com.ingsw.petpal.exception.ResourceNotFoundException;
import com.ingsw.petpal.mapper.ReviewMapper;
import com.ingsw.petpal.model.entity.Carer;
import com.ingsw.petpal.model.entity.Mensajes;
import com.ingsw.petpal.model.entity.Review;
import com.ingsw.petpal.model.entity.User;
import com.ingsw.petpal.repository.CarerRepository;
import com.ingsw.petpal.repository.ReviewRepository;
import com.ingsw.petpal.repository.UserRepository;
import com.ingsw.petpal.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

        private final ReviewRepository reviewRepository;
        private final ReviewMapper reviewMapper;
        private final UserRepository userRepository;
        private final CarerRepository carerRepository;


    @Transactional(readOnly = true)
    @Override
    public List<ReviewDetailsDTO> getAll() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream().map(reviewMapper::toDetailsDTO).toList();
    }


    @Transactional(readOnly = true)
    @Override
    public ReviewDetailsDTO findById(Integer id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resenia no encontrada con id: " + id));
        return reviewMapper.toDetailsDTO(review);

    }

    @Transactional
    @Override
    public ReviewDetailsDTO create(ReviewDTO reviewDTO) {
        Carer carer = carerRepository.findById(reviewDTO.getCuidador()).orElseThrow(() -> new ResourceNotFoundException("carer not found with id: " + reviewDTO.getCuidador()));
        User user = userRepository.findById(reviewDTO.getUsuario()).orElseThrow(() -> new ResourceNotFoundException("user not found with id: " + reviewDTO.getUsuario()));
        Review review = reviewMapper.toEntity(reviewDTO);
        review.setFechaCreacion(LocalDateTime.from(LocalDateTime.now()));
        review.setUsuario(user);
        review.setCuidador(carer);
        return reviewMapper.toDetailsDTO(reviewRepository.save(review));
    }

    @Transactional
    @Override
    public ReviewDetailsDTO update(Integer id, ReviewDTO updatedreviews) {
        Review reviewfromdb = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resenia no encontrado con id " + id));

        Carer carer = carerRepository.findById(updatedreviews.getCuidador()).orElseThrow(() -> new ResourceNotFoundException("carer not found with id: " + updatedreviews.getCuidador()));
        User user = userRepository.findById(updatedreviews.getUsuario()).orElseThrow(() -> new ResourceNotFoundException("user not found with id: " + updatedreviews.getUsuario()));

        reviewfromdb.setFechaCreacion(LocalDateTime.from(LocalDateTime.now()));
        reviewfromdb.setUsuario(user);
        reviewfromdb.setCuidador(carer);
        reviewfromdb.setCalificacion(updatedreviews.getCalificacion());
        reviewfromdb.setComentario(updatedreviews.getComentario());
        return reviewMapper.toDetailsDTO(reviewRepository.save(reviewfromdb));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Review reviewfromdb = reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resenia no encontrado con id " + id));
        reviewRepository.delete(reviewfromdb);
    }
}

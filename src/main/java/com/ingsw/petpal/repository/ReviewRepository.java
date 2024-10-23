package com.ingsw.petpal.repository;

import com.ingsw.petpal.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    Optional<Review> findByCalificacionAndComentario(float calificacion, String comentario);
}

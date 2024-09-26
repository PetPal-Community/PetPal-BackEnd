package com.ingsw.petpal.repository;

import com.ingsw.petpal.model.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentsRepository extends JpaRepository<Comments, Integer> {

    // Método personalizado para encontrar un comentario por su ID
    Optional<Comments> findById(Integer id);

    // Puedes añadir más métodos personalizados si lo necesitas
}

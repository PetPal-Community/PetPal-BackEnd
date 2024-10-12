package com.ingsw.petpal.repository;

import com.ingsw.petpal.model.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComunityRepository extends JpaRepository<Community, Integer> {
    Optional<Community> findByNombre(String nombre);
}

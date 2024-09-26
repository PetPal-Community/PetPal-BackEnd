package com.ingsw.Petpal.repository;

import com.ingsw.Petpal.model.entity.Comunidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComunidadRepository extends JpaRepository<Comunidad, Integer> {
    Optional<Comunidad> findByNombre(String nombre);
}

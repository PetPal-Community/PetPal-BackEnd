package com.ingsw.petpal.repository;

import com.ingsw.petpal.model.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {
    // solo para Validacion -  Optional<Treatment> findByFechaInico(String fechaInico);
}
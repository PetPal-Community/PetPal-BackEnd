package com.ingsw.petpal.repository;

import com.ingsw.petpal.model.entity.Carer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarerRepository extends JpaRepository<Carer, Integer> {
    Optional<Carer> findByNombreAndApellido(String nombre, String apellido);
}
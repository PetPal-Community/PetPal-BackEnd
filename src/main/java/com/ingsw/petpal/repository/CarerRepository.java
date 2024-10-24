package com.ingsw.petpal.repository;

import com.ingsw.petpal.model.entity.Carer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarerRepository extends JpaRepository<Carer, Integer> {
    boolean existsByNombreAndApellido(String nombre, String apellido);
    boolean existsByNombreAndApellidoAndUserIdNot(String firstName, String lastName, Integer userId);

    Optional<Carer> findByNombreAndApellido(String nombre, String apellido);
}
package com.ingsw.petpal.repository;

import com.ingsw.petpal.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByNombreAndApellido(String nombre, String apellido);

    //Optional<User> findByEmail(String email);
    boolean existsByNombreAndApellido(String nombre,String apellido);
    boolean existsByNombreAndApellidoAndUserIdNot(String nombre,String apellido, Integer userId);
}

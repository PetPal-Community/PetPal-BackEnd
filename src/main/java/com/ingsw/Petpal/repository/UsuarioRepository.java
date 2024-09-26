package com.ingsw.Petpal.repository;

import com.ingsw.Petpal.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByNombreAndApellido(String nombre, String apellido);

    Optional<Usuario> findByEmail(String email);
}

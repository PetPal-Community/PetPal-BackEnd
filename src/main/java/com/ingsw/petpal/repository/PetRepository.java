package com.ingsw.petpal.repository;
import com.ingsw.petpal.model.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Integer> {

    // Método para encontrar una mascota por su raza
    Optional<Pet> findByRaza(String raza);

    // Método para contar cuántas mascotas hay por especie
    Long countByEspecie(String especie);
}
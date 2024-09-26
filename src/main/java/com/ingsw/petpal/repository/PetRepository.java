package com.ingsw.petpal.repository;
import com.ingsw.petpal.model.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Integer> {
}
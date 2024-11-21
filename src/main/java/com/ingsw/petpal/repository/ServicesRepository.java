package com.ingsw.petpal.repository;

import com.ingsw.petpal.model.entity.Carer;
import com.ingsw.petpal.model.entity.Pet;
import com.ingsw.petpal.model.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServicesRepository extends JpaRepository<Services, Integer> {
    List<Services> findByCuidador(Carer cuidador);
}

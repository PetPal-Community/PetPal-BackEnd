package com.ingsw.petpal.repository;

import com.ingsw.petpal.model.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {
}

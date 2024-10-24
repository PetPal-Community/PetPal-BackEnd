package com.ingsw.petpal.repository;

import com.ingsw.petpal.model.entity.UserGeneral;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserGeneralRepository extends JpaRepository<UserGeneral, Integer> {
    boolean existsByEmail(String email);
    Optional<UserGeneral> findByEmail(String email);
}

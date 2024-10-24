package com.ingsw.petpal.repository;

import com.ingsw.petpal.model.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServicesRepository extends JpaRepository<Services, Integer> {

}

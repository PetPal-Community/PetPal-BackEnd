package com.ingsw.petpal.repository;

import com.ingsw.petpal.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}

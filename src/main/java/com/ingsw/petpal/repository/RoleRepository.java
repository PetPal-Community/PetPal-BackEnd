package com.ingsw.petpal.repository;

import com.ingsw.petpal.model.entity.Role;
import com.ingsw.petpal.model.entity.enums.ERoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERoles name);
}

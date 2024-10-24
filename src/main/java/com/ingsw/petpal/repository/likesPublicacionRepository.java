package com.ingsw.petpal.repository;

import com.ingsw.petpal.model.entity.likesPublicacion;
import com.ingsw.petpal.model.entity.likesPublicacionFK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface likesPublicacionRepository extends JpaRepository<likesPublicacion, likesPublicacionFK> {
    // Aquí puedes agregar métodos personalizados si los necesitas
}

package com.ingsw.petpal.repository;

import com.ingsw.petpal.model.entity.MedicVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicVisitRepository extends JpaRepository<MedicVisit, Integer> {

}

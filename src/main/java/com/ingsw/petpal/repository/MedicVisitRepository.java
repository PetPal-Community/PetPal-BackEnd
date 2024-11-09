package com.ingsw.petpal.repository;

import com.ingsw.petpal.model.entity.MedicVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MedicVisitRepository extends JpaRepository<MedicVisit, Integer> {

    // Método personalizado para encontrar visitas médicas por fecha de visita
    List<MedicVisit> findByFechaVisita(LocalDate fechaVisita);

    // Método personalizado para encontrar una visita médica por diagnóstico
    Optional<MedicVisit> findByDiagnostico(String diagnostico);



    @Query("SELECT vm FROM MedicVisit vm " +
            "JOIN vm.mascota m " +
            "JOIN m.usuario u " +
            "JOIN u.user p " +
            "WHERE p.id =:personaId")
    List<MedicVisit> findVisitasMedicasByPersonaId(@Param("personaId") Integer personaId);
}
package com.ingsw.petpal.repository;

import com.ingsw.petpal.model.entity.Carer;
import com.ingsw.petpal.model.entity.Contrats;
import com.ingsw.petpal.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContratsRepository extends JpaRepository<Contrats, Integer> {
    List<Contrats> findByCuidador(Carer carer);
    List<Contrats> findByUsuario(User usuario);

    @Query(value = "SELECT count(c.id) as totalContracts, sum(p.valor_pago) as totalAmount " +
            "FROM contrataciones c " +
            "JOIN pagos p ON c.id = p.contratacion_id " +
            "WHERE c.cuidador_id = ( " +
            "   SELECT ca.id " +
            "   FROM personas p " +
            "   JOIN cuidadores ca ON ca.user_id = p.id " +
            "   WHERE p.id = :userGeneralId " +
            ") " +
            "AND EXTRACT(MONTH FROM p.fecha_pago_pagado) = :mes " +
            "AND EXTRACT(YEAR FROM p.fecha_pago_pagado) = :anio",
            nativeQuery = true)
    List<Object[]> getContractReportByMonthAndYear(
            @Param("userGeneralId") Integer userGeneralId,
            @Param("mes") Integer mes,
            @Param("anio") Integer anio);

}

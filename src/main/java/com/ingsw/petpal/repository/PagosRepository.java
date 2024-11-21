package com.ingsw.petpal.repository;

import com.ingsw.petpal.model.entity.Contrats;
import com.ingsw.petpal.model.entity.Pagos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PagosRepository extends JpaRepository<Pagos, Integer> {
    Optional<Pagos> findByMetodoPagoAndEstadoPago(String metodoPago, String estadoPago);

    Pagos findByContrato(Contrats contrato);

}

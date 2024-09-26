package com.ingsw.Petpal.repository;


import com.ingsw.Petpal.model.entity.Pagos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface PagosRepository extends JpaRepository<Pagos, Integer> {
    Optional<Pagos> findByMetodoPagoAndEstadoPago(String metodoPago, String estadoPago);

}
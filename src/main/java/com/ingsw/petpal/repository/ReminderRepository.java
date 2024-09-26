package com.ingsw.petpal.repository;

import com.ingsw.petpal.model.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, Integer> {

    // Ejemplo de un método personalizado para encontrar recordatorios por tipo
    List<Reminder> findByTipoRecordatorio(String tipoRecordatorio);
}

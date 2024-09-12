package com.ingsw.petpal.service;

import com.ingsw.petpal.model.entity.Reminder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminReminderService {

    List<Reminder> findAll();               // Obtener todos los recordatorios
    Page<Reminder> paginate(Pageable pageable); // Paginación de recordatorios
    Reminder create(Reminder reminder);     // Crear un nuevo recordatorio
    Reminder findById(Integer id);          // Buscar un recordatorio por ID
    Reminder update(Integer id, Reminder updatedReminder);  // Actualizar un recordatorio
    void delete(Integer id);                // Eliminar un recordatorio
}

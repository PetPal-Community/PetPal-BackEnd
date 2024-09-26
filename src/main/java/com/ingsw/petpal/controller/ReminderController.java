package com.ingsw.petpal.controller;

import com.ingsw.petpal.model.entity.Reminder;
import com.ingsw.petpal.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reminder")
public class ReminderController {

    private final ReminderService adminReminderService;

    @Autowired
    public ReminderController(ReminderService adminReminderService) {
        this.adminReminderService = adminReminderService;
    }

    // Obtener todos los recordatorios
    @GetMapping
    public ResponseEntity<List<Reminder>> getAllReminders() {
        List<Reminder> reminders = adminReminderService.findAll();
        return new ResponseEntity<>(reminders, HttpStatus.OK);
    }

    // Obtener recordatorios paginados
    @GetMapping("/paginate")
    public ResponseEntity<Page<Reminder>> getRemindersPaginated(Pageable pageable) {
        Page<Reminder> remindersPage = adminReminderService.paginate(pageable);
        return new ResponseEntity<>(remindersPage, HttpStatus.OK);
    }

    // Crear un nuevo recordatorio
    @PostMapping
    public ResponseEntity<Reminder> createReminder(@RequestBody Reminder reminder) {
        Reminder createdReminder = adminReminderService.create(reminder);
        return new ResponseEntity<>(createdReminder, HttpStatus.CREATED);



    }

    // Obtener un recordatorio por ID
    @GetMapping("/{id}")
    public ResponseEntity<Reminder> getReminderById(@PathVariable Integer id) {
        Reminder reminder = adminReminderService.findById(id);
        return new ResponseEntity<>(reminder, HttpStatus.OK);
    }

    // Actualizar un recordatorio por ID
    @PutMapping("/{id}")
    public ResponseEntity<Reminder> updateReminder(
            @PathVariable Integer id,
            @RequestBody Reminder updatedReminder) {
        Reminder reminder = adminReminderService.update(id, updatedReminder);
        return new ResponseEntity<>(reminder, HttpStatus.OK);
    }

    // Eliminar un recordatorio por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReminder(@PathVariable Integer id) {
        adminReminderService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

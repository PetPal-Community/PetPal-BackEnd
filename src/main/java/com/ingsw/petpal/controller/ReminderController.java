package com.ingsw.petpal.controller;

import com.ingsw.petpal.dto.ReminderDTO;
import com.ingsw.petpal.model.entity.Reminder;
import com.ingsw.petpal.service.ReminderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reminder")
public class ReminderController {

    private final ReminderService reminderService;

    @GetMapping
    public ResponseEntity<List<ReminderDTO>> getAllReminders() {
        List<ReminderDTO> reminders = reminderService.getAll();
        return new ResponseEntity<>(reminders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReminderDTO> getReminderById(@PathVariable("id") Integer id) {
        ReminderDTO reminder = reminderService.findById(id);
        return new ResponseEntity<>(reminder, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReminderDTO> createReminder(@Valid @RequestBody ReminderDTO reminderDTO) {
        ReminderDTO createdReminder = reminderService.create(reminderDTO);
        return new ResponseEntity<>(createdReminder, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReminderDTO> updateReminder(@PathVariable("id") Integer id, @Valid @RequestBody ReminderDTO reminderDTO) {
        ReminderDTO updatedReminder = reminderService.update(id, reminderDTO);
        return new ResponseEntity<>(updatedReminder, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReminder(@PathVariable("id") Integer id) {
        reminderService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
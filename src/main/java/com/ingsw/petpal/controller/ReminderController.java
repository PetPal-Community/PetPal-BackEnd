package com.ingsw.petpal.controller;

import com.ingsw.petpal.dto.ReminderCreateUpdateDTO;
import com.ingsw.petpal.dto.ReminderDetailsDTO;
import com.ingsw.petpal.service.ReminderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reminder")
@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
public class ReminderController {

    private final ReminderService reminderService;

    @GetMapping
    public ResponseEntity<List<ReminderDetailsDTO>> getAllReminders() {
        List<ReminderDetailsDTO> reminders = reminderService.findAll();
        return new ResponseEntity<>(reminders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReminderDetailsDTO> getReminderById(@PathVariable Integer id) {
        ReminderDetailsDTO reminder = reminderService.findById(id);
        return new ResponseEntity<>(reminder, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReminderDetailsDTO> createReminder(@Valid @RequestBody ReminderCreateUpdateDTO reminderCreateUpdateDTO) {
        ReminderDetailsDTO createdReminder = reminderService.create(reminderCreateUpdateDTO);
        return new ResponseEntity<>(createdReminder, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReminderDetailsDTO> updateReminder(@PathVariable Integer id, @Valid @RequestBody ReminderCreateUpdateDTO reminderCreateUpdateDTO) {
        ReminderDetailsDTO updatedReminder = reminderService.update(id, reminderCreateUpdateDTO);
        return new ResponseEntity<>(updatedReminder, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReminder(@PathVariable Integer id) {
        reminderService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

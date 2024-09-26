package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.exception.ResourceNotFoundException;
import com.ingsw.petpal.model.entity.Reminder;
import com.ingsw.petpal.repository.ReminderRepository;
import com.ingsw.petpal.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReminderServiceImpl implements ReminderService {

    private final ReminderRepository reminderRepository;

    @Autowired
    public ReminderServiceImpl(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    @Override
    public List<Reminder> findAll() {
        return reminderRepository.findAll();
    }

    @Override
    public Page<Reminder> paginate(Pageable pageable) {
        return reminderRepository.findAll(pageable);
    }

    @Override
    public Reminder create(Reminder reminder) {
        return reminderRepository.save(reminder);
    }

    @Override
    public Reminder findById(Integer id) {
        Optional<Reminder> reminder = reminderRepository.findById(id);
        return reminder.orElseThrow(() -> new ResourceNotFoundException("Reminder not found with id: " + id));
    }

    @Override
    public Reminder update(Integer id, Reminder updatedReminder) {
        Reminder existingReminder = findById(id); // Verifica si existe el recordatorio
        existingReminder.setTipoRecordatorio(updatedReminder.getTipoRecordatorio());
        existingReminder.setHora(updatedReminder.getHora());
        existingReminder.setDescripcion(updatedReminder.getDescripcion());
        existingReminder.setMascota(updatedReminder.getMascota());
        return reminderRepository.save(existingReminder); // Actualiza y guarda
    }

    @Override
    public void delete(Integer id) {
        Reminder reminder = findById(id); // Verifica si existe el recordatorio
        reminderRepository.delete(reminder);
    }
}
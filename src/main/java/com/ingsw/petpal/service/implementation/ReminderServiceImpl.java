package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.dto.ReminderDTO;
import com.ingsw.petpal.exception.ResourceNotFoundException;
import com.ingsw.petpal.mapper.ReminderMapper;
import com.ingsw.petpal.model.entity.Reminder;
import com.ingsw.petpal.repository.ReminderRepository;
import com.ingsw.petpal.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReminderServiceImpl implements ReminderService {

    private final ReminderRepository reminderRepository;
    private final ReminderMapper reminderMapper;

    @Transactional(readOnly = true)
    @Override
    public List<ReminderDTO> getAll() {
        List<Reminder> reminders = reminderRepository.findAll();
        return reminders.stream()
                .map(reminderMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public ReminderDTO findById(Integer id) {
        Reminder reminder = reminderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recordatorio no encontrado con id: " + id));
        return reminderMapper.toDTO(reminder);
    }

    @Transactional
    @Override
    public ReminderDTO create(ReminderDTO reminderDTO) {
        // Aquí puedes agregar validaciones adicionales si es necesario
        Reminder reminder = reminderMapper.toEntity(reminderDTO);
        reminder = reminderRepository.save(reminder);
        return reminderMapper.toDTO(reminder);
    }

    @Transactional
    @Override
    public ReminderDTO update(Integer id, ReminderDTO updatedReminderDTO) {
        Reminder reminderFromDb = reminderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recordatorio no encontrado con id: " + id));

        // Aquí puedes agregar validaciones adicionales si es necesario
        reminderFromDb.setTipoRecordatorio(updatedReminderDTO.getTipoRecordatorio());
        reminderFromDb.setDescripcion(updatedReminderDTO.getDescripcion());
        reminderFromDb.setHora(updatedReminderDTO.getHora());

        reminderFromDb = reminderRepository.save(reminderFromDb);
        return reminderMapper.toDTO(reminderFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Reminder reminderFromDb = reminderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recordatorio no encontrado con id: " + id));
        reminderRepository.delete(reminderFromDb);
    }
}
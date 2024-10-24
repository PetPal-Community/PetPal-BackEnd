package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.dto.ReminderCreateUpdateDTO;
import com.ingsw.petpal.dto.ReminderDetailsDTO;
import com.ingsw.petpal.mapper.ReminderMapper;
import com.ingsw.petpal.model.entity.Reminder;
import com.ingsw.petpal.repository.ReminderRepository;
import com.ingsw.petpal.service.ReminderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReminderServiceImpl implements ReminderService {
    private final ReminderRepository reminderRepository;
    private final ReminderMapper reminderMapper;

    @Override
    public List<ReminderDetailsDTO> findAll() {
        return reminderRepository.findAll()
                .stream()
                .map(reminderMapper::toDetailsDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReminderDetailsDTO findById(Integer id) {
        Reminder reminder = reminderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reminder not found"));
        return reminderMapper.toDetailsDTO(reminder);
    }

    @Override
    @Transactional
    public ReminderDetailsDTO create(ReminderCreateUpdateDTO reminderCreateUpdateDTO) {
        Reminder reminder = reminderMapper.toEntity(reminderCreateUpdateDTO);
        Reminder savedReminder = reminderRepository.save(reminder);
        return reminderMapper.toDetailsDTO(savedReminder);
    }

    @Override
    @Transactional
    public ReminderDetailsDTO update(Integer id, ReminderCreateUpdateDTO reminderCreateUpdateDTO) {
        Reminder reminder = reminderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reminder not found"));

        // Actualiza los campos
        reminder.setTipoRecordatorio(reminderCreateUpdateDTO.getTipoRecordatorio());
        reminder.setDescripcion(reminderCreateUpdateDTO.getDescripcion());
        reminder.setHora(reminderCreateUpdateDTO.getHora());

        Reminder updatedReminder = reminderRepository.save(reminder);
        return reminderMapper.toDetailsDTO(updatedReminder);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Reminder reminder = reminderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reminder not found"));
        reminderRepository.delete(reminder);
    }
}

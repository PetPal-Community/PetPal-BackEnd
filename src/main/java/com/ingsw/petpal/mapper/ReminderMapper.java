package com.ingsw.petpal.mapper;

import com.ingsw.petpal.dto.ReminderDTO;
import com.ingsw.petpal.model.entity.Reminder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReminderMapper {

    private final ModelMapper modelMapper;

    // Convierte de entidad a DTO
    public ReminderDTO toDTO(Reminder reminder) {
        return modelMapper.map(reminder, ReminderDTO.class);
    }

    // Convierte de DTO a entidad
    public Reminder toEntity(ReminderDTO reminderDTO) {
        return modelMapper.map(reminderDTO, Reminder.class);
    }
}
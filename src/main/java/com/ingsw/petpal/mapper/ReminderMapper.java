package com.ingsw.petpal.mapper;

import com.ingsw.petpal.dto.ReminderCreateUpdateDTO;
import com.ingsw.petpal.dto.ReminderDetailsDTO;
import com.ingsw.petpal.model.entity.Reminder;
import com.ingsw.petpal.model.entity.Pet;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ReminderMapper {
    private final ModelMapper modelMapper;

    public ReminderMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // Método para convertir Reminder a ReminderDetailsDTO
    public ReminderDetailsDTO toDetailsDTO(Reminder reminder) {
        ReminderDetailsDTO detailsDTO = modelMapper.map(reminder, ReminderDetailsDTO.class);
        // Establecer mascotaId si la mascota no es nula
        if (reminder.getMascota() != null) {
            detailsDTO.setMascotaId(reminder.getMascota().getId());
        }
        return detailsDTO;
    }

    // Método para convertir ReminderCreateUpdateDTO a Reminder
    public Reminder toEntity(ReminderCreateUpdateDTO reminderCreateUpdateDTO) {
        Reminder reminder = modelMapper.map(reminderCreateUpdateDTO, Reminder.class);
        // Establecer la mascota usando el ID que recibas en el DTO
        if (reminderCreateUpdateDTO.getMascotaId() != null) {
            Pet pet = new Pet();
            pet.setId(reminderCreateUpdateDTO.getMascotaId()); // Asignar el ID de la mascota
            reminder.setMascota(pet);
        }
        return reminder;
    }
}

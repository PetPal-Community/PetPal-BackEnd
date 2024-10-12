package com.ingsw.petpal.service;

import com.ingsw.petpal.dto.ReminderDTO;
import com.ingsw.petpal.model.entity.Reminder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReminderService {

    List<ReminderDTO> getAll();

    ReminderDTO findById(Integer id);

    ReminderDTO create(ReminderDTO reminderDTO);

    ReminderDTO update(Integer id, ReminderDTO reminderDTO);

    void delete(Integer id);
}

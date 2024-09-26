package com.ingsw.petpal.service;

import com.ingsw.petpal.dto.ReminderDTO;

import java.util.List;

public interface ReminderService {

    List<ReminderDTO> getAll();

    ReminderDTO findById(Integer id);

    ReminderDTO create(ReminderDTO reminderDTO);

    ReminderDTO update(Integer id, ReminderDTO reminderDTO);

    void delete(Integer id);
}

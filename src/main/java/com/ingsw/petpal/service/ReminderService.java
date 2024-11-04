package com.ingsw.petpal.service;

import com.ingsw.petpal.dto.ReminderCreateUpdateDTO;
import com.ingsw.petpal.dto.ReminderDetailsDTO;

import java.util.List;

public interface ReminderService {
    List<ReminderDetailsDTO> findAll();
    ReminderDetailsDTO findById(Integer id);
    ReminderDetailsDTO create(ReminderCreateUpdateDTO reminderCreateUpdateDTO);
    ReminderDetailsDTO update(Integer id, ReminderCreateUpdateDTO reminderCreateUpdateDTO);
    void delete(Integer id);
}

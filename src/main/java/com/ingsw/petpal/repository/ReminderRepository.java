package com.ingsw.petpal.repository;

import com.ingsw.petpal.model.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReminderRepository extends JpaRepository<Reminder, Integer> {

}
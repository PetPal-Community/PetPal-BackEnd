package com.ingsw.petpal.service;


import com.ingsw.petpal.model.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User create(User user);
    User findById(int id);
    User update(User user);

    @Transactional
    User update(Integer id, User updateUser);

    void delete(Integer id);
}

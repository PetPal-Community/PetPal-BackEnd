package com.ingsw.petpal.service.implementation;


import com.ingsw.petpal.model.entity.User;
import com.ingsw.petpal.repository.UserRepository;
import com.ingsw.petpal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImplementation implements UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(int id) {
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not found" + id));
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Transactional
    @Override
    public User update(Integer id, User updateUser) {
        User userFromDb = findById(id);
        userFromDb.setNombre(updateUser.getNombre());
        userFromDb.setApellido(updateUser.getApellido());
        userFromDb.setEmail(updateUser.getEmail());
        userFromDb.setTelefono(updateUser.getTelefono());
        userFromDb.setContrasenia(updateUser.getContrasenia());
        return userRepository.save(userFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        User userFromDb = findById(id);
        userRepository.delete(userFromDb);
    }
}


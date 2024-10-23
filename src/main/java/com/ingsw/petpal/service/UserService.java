package com.ingsw.petpal.service;

import com.ingsw.petpal.dto.UsuarioDTO;
import com.ingsw.petpal.model.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    List<UsuarioDTO> getAll();

    UsuarioDTO findById(Integer id);

    UsuarioDTO create(UsuarioDTO usuarioDTO);

    UsuarioDTO update(Integer id, UsuarioDTO updatedUsuarioDTO);



    void delete(Integer id);
}


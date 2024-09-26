package com.ingsw.Petpal.service;

import com.ingsw.Petpal.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {
    List<UsuarioDTO> getAll();

    UsuarioDTO findById(Integer id);

    UsuarioDTO create(UsuarioDTO usuarioDTO);

    UsuarioDTO update(Integer id, UsuarioDTO updatedUsuarioDTO);



    void delete(Integer id);
}

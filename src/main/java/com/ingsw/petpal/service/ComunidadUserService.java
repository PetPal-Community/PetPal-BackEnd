package com.ingsw.petpal.service;

import com.ingsw.petpal.model.entity.ComunidadUser;
import com.ingsw.petpal.model.entity.User;

import java.util.List;

public interface ComunidadUserService {
    ComunidadUser addUsuarioToComunidad(Integer usuarioId, Integer comunidadId);

    void removeUsuarioFromComunidad(Integer usuarioId, Integer comunidadId);

    List<User> getUsuariosInComunidad(Integer comunidadId);
}

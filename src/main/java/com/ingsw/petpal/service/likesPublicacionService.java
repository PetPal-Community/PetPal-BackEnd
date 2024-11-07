package com.ingsw.petpal.service;

import com.ingsw.petpal.model.entity.User;
import com.ingsw.petpal.model.entity.likesPublicacion;

import java.util.List;

public interface likesPublicacionService {
    likesPublicacion anadirLikeAPublicacion(Integer userID, Integer publicacionID);
    void eliminarLikeAPublicacion(Integer userID, Integer publicacionID);
    List<User> obtenerQuienesDieronLikeAPubli(Integer publicacionID);
}

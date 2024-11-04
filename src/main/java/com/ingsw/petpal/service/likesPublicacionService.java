package com.ingsw.petpal.service;

import com.ingsw.petpal.dto.likesPublicacionDTO;

import java.util.List;

public interface likesPublicacionService {
    void darLike(likesPublicacionDTO likesPublicacionDTO);
    void quitarLike(likesPublicacionDTO likesPublicacionDTO);
    List<likesPublicacionDTO> obtenerTodosLosLikes();
}

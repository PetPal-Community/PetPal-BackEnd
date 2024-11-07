package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.exception.BadRequestException;
import com.ingsw.petpal.model.entity.likesPublicacion;
import com.ingsw.petpal.model.entity.likesPublicacionFK;
import com.ingsw.petpal.model.entity.Publicaciones;
import com.ingsw.petpal.model.entity.User;
import com.ingsw.petpal.repository.PublicacionesRepository;
import com.ingsw.petpal.repository.likesPublicacionRepository;
import com.ingsw.petpal.service.likesPublicacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class likesPublicacionServiceImpl implements likesPublicacionService {

    private final likesPublicacionRepository likesPubliRepository;
    private final PublicacionesRepository publicacionesRepository;

    @Override
    @Transactional
    public likesPublicacion anadirLikeAPublicacion(Integer userID, Integer publicacionID){
        if (likesPubliRepository.existsByUsuarioAndPublicacion(userID, publicacionID)) {
            throw new BadRequestException("Este Usuario ya ha dado like a esta publicacion.");
        }

        // Establecer la fecha en que se agrega
        LocalDateTime fechaCreada = LocalDateTime.now();

        // Añadimos a la publi
        likesPubliRepository.addLikesPublicacion(userID, publicacionID, fechaCreada);

        // creamos el objeto a devolver
        likesPublicacion likPublicacion = new likesPublicacion();
        likPublicacion.setUsuario(userID);
        likPublicacion.setPublicacion(publicacionID);
        likPublicacion.setFecha(fechaCreada);
        return likPublicacion;
    }



    @Override
    @Transactional
    public void eliminarLikeAPublicacion(Integer userID, Integer publicacionID){
        likesPubliRepository.deleteByUsuarioAndPublicacion(userID, publicacionID);
    }

    @Override
    public List<User> obtenerQuienesDieronLikeAPubli(Integer publicacionID){
        Publicaciones publi = publicacionesRepository.findById(publicacionID)
                .orElseThrow(() -> new RuntimeException("No se encontro la publi"));

        return likesPubliRepository.findUsuariosByPublicacion(publi);
    }


}

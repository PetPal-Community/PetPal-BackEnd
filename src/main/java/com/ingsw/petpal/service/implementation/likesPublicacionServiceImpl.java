package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.dto.likesPublicacionDTO;
import com.ingsw.petpal.model.entity.likesPublicacion;
import com.ingsw.petpal.model.entity.likesPublicacionFK;
import com.ingsw.petpal.model.entity.Publicaciones;
import com.ingsw.petpal.model.entity.User;
import com.ingsw.petpal.repository.likesPublicacionRepository;
import com.ingsw.petpal.service.likesPublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class likesPublicacionServiceImpl implements likesPublicacionService {

    @Autowired
    private likesPublicacionRepository likesPublicacionRepository;

    // Método para dar un like
    @Override
    public void darLike(likesPublicacionDTO likesPublicacionDTO) {
        likesPublicacion like = new likesPublicacion();
        likesPublicacionFK pk = new likesPublicacionFK();

        User usuario = new User();
        usuario.setId(likesPublicacionDTO.getUsuarioId());

        Publicaciones publicacion = new Publicaciones();
        publicacion.setId(likesPublicacionDTO.getPublicacionId());

        pk.setUsuario(usuario);
        pk.setPublicacion(publicacion);

        like.setUsuario(usuario);
        like.setPublicacion(publicacion);
        like.setFecha(likesPublicacionDTO.getFecha());

        likesPublicacionRepository.save(like);
    }

    // Método para quitar un like
    @Override
    public void quitarLike(likesPublicacionDTO likesPublicacionDTO) {
        // Crear una instancia de likesPublicacionFK para buscar el like
        likesPublicacionFK pk = new likesPublicacionFK();

        User usuario = new User();
        usuario.setId(likesPublicacionDTO.getUsuarioId());

        Publicaciones publicacion = new Publicaciones();
        publicacion.setId(likesPublicacionDTO.getPublicacionId());

        pk.setUsuario(usuario);
        pk.setPublicacion(publicacion);

        // Eliminar el like correspondiente
        likesPublicacionRepository.deleteById(pk);
    }

    // Método para obtener todos los likes
    @Override
    public List<likesPublicacionDTO> obtenerTodosLosLikes() {
        List<likesPublicacion> likes = likesPublicacionRepository.findAll();
        return likes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Método para convertir de entidad a DTO
    private likesPublicacionDTO convertToDTO(likesPublicacion like) {
        likesPublicacionDTO dto = new likesPublicacionDTO();
        dto.setUsuarioId(like.getUsuario().getId());
        dto.setPublicacionId(like.getPublicacion().getId());
        dto.setFecha(like.getFecha());
        return dto;
    }
}

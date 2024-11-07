package com.ingsw.petpal.controller;

import com.ingsw.petpal.model.entity.User;
import com.ingsw.petpal.model.entity.likesPublicacion;
import com.ingsw.petpal.service.likesPublicacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likespublicacion")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
public class likesPublicacionController {

    private final likesPublicacionService likesPublicacionService;

    // Método para dar like a una publicación
    @PostMapping("/like/{publicacionID}")
    public ResponseEntity<likesPublicacion> darLike(@PathVariable Integer publicacionID, @RequestParam Integer userID) {
        likesPublicacion likPubli = likesPublicacionService.anadirLikeAPublicacion(userID, publicacionID);
        return new ResponseEntity<>(likPubli, HttpStatus.CREATED);
    }

    // Método para quitar un like de una publicación
    @PostMapping("/{publicacionID}/unlike/{userID}")
    public ResponseEntity<Void> quitarLike(@PathVariable Integer publicacionID, @PathVariable Integer userID) {
        likesPublicacionService.eliminarLikeAPublicacion(userID, publicacionID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Método para obtener likes de una publi
    @GetMapping("/{publicacionID}/users")
    public ResponseEntity<List<User>> obtenerLikesDePubli(@PathVariable Integer publicacionID) {
        List<User> likes = likesPublicacionService.obtenerQuienesDieronLikeAPubli(publicacionID);
        return ResponseEntity.ok(likes);
    }
}

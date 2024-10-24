package com.ingsw.petpal.controller;

import com.ingsw.petpal.dto.likesPublicacionDTO;
import com.ingsw.petpal.service.likesPublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likespublicacion")
public class likesPublicacionController {

    @Autowired
    private likesPublicacionService likesPublicacionService;

    // Método para dar like a una publicación
    @PostMapping("/like")
    public ResponseEntity<String> darLike(@RequestBody likesPublicacionDTO likesPublicacionDTO) {
        likesPublicacionService.darLike(likesPublicacionDTO);
        return ResponseEntity.ok("Like agregado");
    }

    // Método para quitar un like de una publicación
    @PostMapping("/unlike")
    public ResponseEntity<String> quitarLike(@RequestBody likesPublicacionDTO likesPublicacionDTO) {
        likesPublicacionService.quitarLike(likesPublicacionDTO);
        return ResponseEntity.ok("Like eliminado");
    }

    // Método para obtener todos los likes
    @GetMapping
    public ResponseEntity<List<likesPublicacionDTO>> obtenerTodosLosLikes() {
        List<likesPublicacionDTO> likes = likesPublicacionService.obtenerTodosLosLikes();
        return ResponseEntity.ok(likes);
    }
}

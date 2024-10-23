package com.ingsw.petpal.controller;

import com.ingsw.petpal.dto.PublicacionCreateDTO;
import com.ingsw.petpal.dto.PublicacionDetailsDTO;
import com.ingsw.petpal.model.entity.Publicaciones;
import com.ingsw.petpal.model.entity.Treatment;
import com.ingsw.petpal.service.PublicacionesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/publicaciones")
@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
public class PublicacionController {
    private final PublicacionesService publicacionesService;

    @GetMapping
    public ResponseEntity<List<PublicacionDetailsDTO>> getAll(){
        List<PublicacionDetailsDTO> publicaciones = publicacionesService.findAll();
        return new ResponseEntity<List<PublicacionDetailsDTO>>(publicaciones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDetailsDTO> getPublicacionById(@PathVariable("id") int id){
        PublicacionDetailsDTO  publicaciones = publicacionesService.findById(id);
        return new ResponseEntity<PublicacionDetailsDTO>(publicaciones, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PublicacionDetailsDTO> createPublicacion(@Valid @RequestBody PublicacionCreateDTO publicaciones){
        PublicacionDetailsDTO publicacionCreada = publicacionesService.create(publicaciones);
        return new ResponseEntity<PublicacionDetailsDTO>(publicacionCreada, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicacionDetailsDTO> updatePublicacion(@PathVariable("id") int id,@Valid @RequestBody PublicacionCreateDTO publicacionesFromDTO){
        PublicacionDetailsDTO updatePublicacion = publicacionesService.update(id,publicacionesFromDTO);
        return new ResponseEntity<>(updatePublicacion, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublicacion(@PathVariable("id") Integer id){
        publicacionesService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.ingsw.petpal.controller;

import com.ingsw.petpal.dto.ComentsCreateUpdateDTO;
import com.ingsw.petpal.dto.ComentsDetailsDTO;
import com.ingsw.petpal.service.ComentsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comentarios")
@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
public class ComentsController {
    private final ComentsService comentsService;

    @GetMapping
    public ResponseEntity<List<ComentsDetailsDTO>> getComentsAll() {
        List<ComentsDetailsDTO> comentarios = comentsService.findAll();
        return new ResponseEntity<List<ComentsDetailsDTO>>(comentarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComentsDetailsDTO> getComentsById(@PathVariable Integer id) {
        ComentsDetailsDTO comentario = comentsService.findById(id);
        return new ResponseEntity<ComentsDetailsDTO>(comentario, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ComentsDetailsDTO> createComents(@Valid @RequestBody ComentsCreateUpdateDTO comentario) {
        ComentsDetailsDTO comentsDetailsDTO = comentsService.create(comentario);
        return new ResponseEntity<ComentsDetailsDTO>(comentsDetailsDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComentsDetailsDTO> updateComents(@PathVariable Integer id,@Valid @RequestBody ComentsCreateUpdateDTO comentario) {
        ComentsDetailsDTO comentsDetailsDTO = comentsService.update(id, comentario);
        return new ResponseEntity<>(comentsDetailsDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ComentsDetailsDTO> deleteComents(@PathVariable Integer id) {
        comentsService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

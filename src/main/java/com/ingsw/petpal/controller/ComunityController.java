package com.ingsw.petpal.controller;

import com.ingsw.petpal.dto.CommunityDTO;
import com.ingsw.petpal.model.entity.Community;
import com.ingsw.petpal.service.ComunityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comunidades")
public class ComunityController {

    private final ComunityService comunidadService;

    @GetMapping
    public ResponseEntity<List<CommunityDTO>> getAllComunidades() {
        List<CommunityDTO> comunidades = comunidadService.getAll();
        return new ResponseEntity<>(comunidades, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommunityDTO> getComunidadById(@PathVariable("id") Integer id) {
        CommunityDTO comunidad = comunidadService.findById(id);
        return new ResponseEntity<>(comunidad, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommunityDTO> createComunidad(@Valid @RequestBody CommunityDTO comunidadDTO) {
        CommunityDTO createdComunidad = comunidadService.create(comunidadDTO);
        return new ResponseEntity<>(createdComunidad, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommunityDTO> updateComunidad(@PathVariable("id") Integer id, @Valid @RequestBody CommunityDTO comunidadDTO) {
        CommunityDTO updatedComunidad = comunidadService.update(id, comunidadDTO);
        return new ResponseEntity<>(updatedComunidad, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComunidad(@PathVariable("id") Integer id) {
        comunidadService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
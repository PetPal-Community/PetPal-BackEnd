package com.ingsw.Petpal.api;

import com.ingsw.Petpal.dto.ComunidadDTO;
import com.ingsw.Petpal.service.ComunidadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comunidades")
public class ComunidadController {

    private final ComunidadService comunidadService;

    @GetMapping
    public ResponseEntity<List<ComunidadDTO>> getAllComunidades() {
        List<ComunidadDTO> comunidades = comunidadService.getAll();
        return new ResponseEntity<>(comunidades, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComunidadDTO> getComunidadById(@PathVariable("id") Integer id) {
        ComunidadDTO comunidad = comunidadService.findById(id);
        return new ResponseEntity<>(comunidad, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ComunidadDTO> createComunidad(@Valid @RequestBody ComunidadDTO comunidadDTO) {
        ComunidadDTO createdComunidad = comunidadService.create(comunidadDTO);
        return new ResponseEntity<>(createdComunidad, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComunidadDTO> updateComunidad(@PathVariable("id") Integer id, @Valid @RequestBody ComunidadDTO comunidadDTO) {
        ComunidadDTO updatedComunidad = comunidadService.update(id, comunidadDTO);
        return new ResponseEntity<>(updatedComunidad, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComunidad(@PathVariable("id") Integer id) {
        comunidadService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.ingsw.petpal.controller;

import com.ingsw.petpal.dto.CarerDTO;
import com.ingsw.petpal.service.CarerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/carer")

public class CarerController {
    private final CarerService carerService;

    @GetMapping
    public ResponseEntity<List<CarerDTO>> getAllCarers() {
        List<CarerDTO> carers = carerService.getAll();
        return new ResponseEntity<List<CarerDTO>>(carers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarerDTO> getCarerById(@PathVariable("id") Integer id) {
        CarerDTO carer = carerService.findById(id);
        return new ResponseEntity<CarerDTO>(carer, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CarerDTO> createUsuario(@Valid @RequestBody CarerDTO carerDTO) {
        CarerDTO createdCarer = carerService.create(carerDTO);
        return new ResponseEntity<CarerDTO>(createdCarer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarerDTO> updateCarer(@PathVariable("id") Integer id, @Valid @RequestBody CarerDTO carerDTO) {
        CarerDTO updatedCarer = carerService.update(id, carerDTO);
        return new ResponseEntity<CarerDTO>(updatedCarer, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<CarerDTO> deleteCarer(@PathVariable("id") Integer id) {
        carerService.delete(id);
        return new ResponseEntity<CarerDTO>(HttpStatus.NO_CONTENT);
    }
}

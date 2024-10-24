package com.ingsw.petpal.controller;

import com.ingsw.petpal.dto.TreatmentCreateUpdateDTO;
import com.ingsw.petpal.dto.TreatmentDetailsDTO;

import com.ingsw.petpal.service.TreatmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/treatments")
@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
public class TreatmentController {
    private final TreatmentService treatmentService;


    @GetMapping
    public ResponseEntity<List<TreatmentDetailsDTO>> getTreatmentsAll() {
        List<TreatmentDetailsDTO> treatments = treatmentService.findAll();
        return new ResponseEntity<List<TreatmentDetailsDTO>>(treatments,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreatmentDetailsDTO> getTreatmentId(@PathVariable("id") Integer id){
        TreatmentDetailsDTO treatment = treatmentService.findById(id);
        return new ResponseEntity<TreatmentDetailsDTO>(treatment,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TreatmentDetailsDTO> create(@Valid @RequestBody TreatmentCreateUpdateDTO treatmentFormDTO) {
        TreatmentDetailsDTO treatmentCreated = treatmentService.create(treatmentFormDTO);
        return new ResponseEntity<TreatmentDetailsDTO>(treatmentCreated,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreatmentDetailsDTO> update(@PathVariable Integer id, @Valid @RequestBody TreatmentCreateUpdateDTO bookFromDTO){
        TreatmentDetailsDTO updateTreatment= treatmentService.update(id, bookFromDTO);
        return new ResponseEntity<>(updateTreatment, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        treatmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

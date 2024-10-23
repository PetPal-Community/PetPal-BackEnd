package com.ingsw.petpal.controller;

import com.ingsw.petpal.dto.MedicDocumentsCreateUpdateDTO;
import com.ingsw.petpal.dto.MedicDocumentsDetailsDTO;
import com.ingsw.petpal.service.MedicDocumentsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/documents")
@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
public class MedicDocumentsController {

    private final MedicDocumentsService medicDocumentsService;

    @GetMapping
    public ResponseEntity<List<MedicDocumentsDetailsDTO>> getMedicDocumentsAll() {
        List<MedicDocumentsDetailsDTO> medicDocuments = medicDocumentsService.findAll();
        return new ResponseEntity<List<MedicDocumentsDetailsDTO>>(medicDocuments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicDocumentsDetailsDTO> getMedicDocumentId(@PathVariable Integer id){
        MedicDocumentsDetailsDTO medicDocuments = medicDocumentsService.findById(id);
        return new ResponseEntity<MedicDocumentsDetailsDTO>(medicDocuments, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<MedicDocumentsDetailsDTO> create(@Valid @RequestBody MedicDocumentsCreateUpdateDTO medicDocumentsFromDTO) {
        MedicDocumentsDetailsDTO medicDocumentsCreated = medicDocumentsService.create(medicDocumentsFromDTO);
        return new ResponseEntity<MedicDocumentsDetailsDTO>(medicDocumentsCreated, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<MedicDocumentsDetailsDTO> update(@PathVariable Integer id, @Valid @RequestBody MedicDocumentsCreateUpdateDTO medicDocumentsFromDTO){
        MedicDocumentsDetailsDTO updateMedicDocuments = medicDocumentsService.update(id, medicDocumentsFromDTO);
        return new ResponseEntity<>(updateMedicDocuments, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        medicDocumentsService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
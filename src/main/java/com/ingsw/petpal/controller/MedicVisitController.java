package com.ingsw.petpal.controller;

import com.ingsw.petpal.dto.MedicVisitCreateUpdateDTO;
import com.ingsw.petpal.dto.MedicVisitDetailsDTO;
import com.ingsw.petpal.service.MedicVisitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/medicvisit")
public class MedicVisitController {
    private final MedicVisitService medicVisitService;

    @GetMapping
    public ResponseEntity<List<MedicVisitDetailsDTO>> getAllMedicVisits() {
        List<MedicVisitDetailsDTO> visits = medicVisitService.findAll();
        return new ResponseEntity<>(visits, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicVisitDetailsDTO> getMedicVisitById(@PathVariable Integer id) {
        MedicVisitDetailsDTO visit = medicVisitService.findById(id);
        return new ResponseEntity<>(visit, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MedicVisitDetailsDTO> createMedicVisit(@Valid @RequestBody MedicVisitCreateUpdateDTO medicVisitCreateUpdateDTO) {
        MedicVisitDetailsDTO createdVisit = medicVisitService.create(medicVisitCreateUpdateDTO);
        return new ResponseEntity<>(createdVisit, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicVisitDetailsDTO> updateMedicVisit(@PathVariable Integer id, @Valid @RequestBody MedicVisitCreateUpdateDTO medicVisitCreateUpdateDTO) {
        MedicVisitDetailsDTO updatedVisit = medicVisitService.update(id, medicVisitCreateUpdateDTO);
        return new ResponseEntity<>(updatedVisit, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicVisit(@PathVariable Integer id) {
        medicVisitService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

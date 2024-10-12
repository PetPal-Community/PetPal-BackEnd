package com.ingsw.petpal.controller;

import com.ingsw.petpal.dto.MedicVisitDTO;
import com.ingsw.petpal.model.entity.MedicVisit;
import com.ingsw.petpal.model.entity.User;
import com.ingsw.petpal.service.MedicVisitService;
import com.ingsw.petpal.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequiredArgsConstructor
@RequestMapping("/medicvisit")
public class MedicVisitController {

    private final MedicVisitService medicVisitService;

    @GetMapping
    public ResponseEntity<List<MedicVisitDTO>> getAllMedicVisits() {
        List<MedicVisitDTO> visits = medicVisitService.getAll();
        return new ResponseEntity<>(visits, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicVisitDTO> getMedicVisitById(@PathVariable("id") Integer id) {
        MedicVisitDTO visit = medicVisitService.findById(id);
        return new ResponseEntity<>(visit, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MedicVisitDTO> createMedicVisit(@Valid @RequestBody MedicVisitDTO medicVisitDTO) {
        MedicVisitDTO createdVisit = medicVisitService.create(medicVisitDTO);
        return new ResponseEntity<>(createdVisit, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicVisitDTO> updateMedicVisit(@PathVariable("id") Integer id, @Valid @RequestBody MedicVisitDTO medicVisitDTO) {
        MedicVisitDTO updatedVisit = medicVisitService.update(id, medicVisitDTO);
        return new ResponseEntity<>(updatedVisit, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicVisit(@PathVariable("id") Integer id) {
        medicVisitService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
package com.ingsw.petpal.controller;

import com.ingsw.petpal.model.entity.MedicVisit;
import com.ingsw.petpal.service.AdminMedicVisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RequiredArgsConstructor
@RestController
@RequestMapping("/medicvisit")
public class MedicVisitController {

    @Autowired
    private AdminMedicVisitService adminMedicVisitService;

    @PostMapping
    public ResponseEntity<MedicVisit> createMedicVisit(@RequestBody MedicVisit medicVisit) {
        MedicVisit createdVisit = adminMedicVisitService.createMedicVisit(medicVisit);
        return new ResponseEntity<>(createdVisit, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicVisit> getMedicVisitById(@PathVariable Integer id) {
        Optional<MedicVisit> medicVisit = adminMedicVisitService.getMedicVisitById(id);
        return medicVisit.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<MedicVisit>> getAllMedicVisits(Pageable pageable) {
        Page<MedicVisit> medicVisits = adminMedicVisitService.getAllMedicVisits(pageable);
        return ResponseEntity.ok(medicVisits);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicVisit> updateMedicVisit(@PathVariable Integer id, @RequestBody MedicVisit medicVisit) {
        MedicVisit updatedVisit = adminMedicVisitService.updateMedicVisit(id, medicVisit);
        return updatedVisit != null ? ResponseEntity.ok(updatedVisit)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicVisit(@PathVariable Integer id) {
        adminMedicVisitService.deleteMedicVisit(id);
        return ResponseEntity.noContent().build();
    }
}

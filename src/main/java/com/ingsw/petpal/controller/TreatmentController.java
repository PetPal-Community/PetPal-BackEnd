package com.ingsw.petpal.controller;

import com.ingsw.petpal.model.entity.Treatment;
import com.ingsw.petpal.service.TreatmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/treatments")

public class TreatmentController {
    private final TreatmentService treatmentService;


    @GetMapping
    public List<Treatment> list() {
        return treatmentService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Treatment create(@RequestBody Treatment treatment) {
        return treatmentService.create(treatment);
    }

    @GetMapping("/{id}")
    public Treatment get(@PathVariable Integer id){
        return treatmentService.findById(id);
    }

    @PutMapping("/{id}")
    public Treatment update(@PathVariable Integer id, @RequestBody Treatment treatment){
        return treatmentService.update(id, treatment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        treatmentService.delete(id);
    }


}

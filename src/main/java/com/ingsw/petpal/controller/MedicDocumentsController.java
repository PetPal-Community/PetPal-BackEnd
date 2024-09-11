package com.ingsw.petpal.controller;

import com.ingsw.petpal.model.entity.MedicDocuments;
import com.ingsw.petpal.service.MedicDocumentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/documents")
public class MedicDocumentsController {

    private final MedicDocumentsService medicDocumentsService;

    @GetMapping
    public List<MedicDocuments> list() {
        return medicDocumentsService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MedicDocuments create(@RequestBody MedicDocuments medicDocuments) {
        return medicDocumentsService.create(medicDocuments);
    }

    @GetMapping("/{id}")
    public MedicDocuments get(@PathVariable Integer id){
        return medicDocumentsService.findById(id);
    }

    @PutMapping("/{id}")
    public MedicDocuments update(@PathVariable Integer id, @RequestBody MedicDocuments medicDocuments){
        return medicDocumentsService.update(id, medicDocuments);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        medicDocumentsService.delete(id);
    }

}

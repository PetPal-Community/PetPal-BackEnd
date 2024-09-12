package com.ingsw.petpal.controller;

import com.ingsw.petpal.model.entity.Contrats;
import com.ingsw.petpal.service.ContratsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contrats")

public class ContratsController {
    private final ContratsService contratsService;


    @GetMapping
    public List<Contrats> list() {
        return contratsService.findAll();
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Contrats create(@RequestBody Contrats contrats) {
        return contratsService.create(contrats);
    }

    @GetMapping("/{id}")
    public Contrats get(@PathVariable Integer id){
        return contratsService.findById(id);
    }

    @PutMapping("/{id}")
    public Contrats update(@PathVariable Integer id, @RequestBody Contrats contrats) {
        return contratsService.update(id, contrats);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        contratsService.delete(id);
    }

}

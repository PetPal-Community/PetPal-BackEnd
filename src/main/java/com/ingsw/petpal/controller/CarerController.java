package com.ingsw.petpal.controller;

import com.ingsw.petpal.model.entity.Carer;
import com.ingsw.petpal.service.CarerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/carer")
@PreAuthorize("hasRole('ADMIN')")  // editar para que el carer pueda tener control para su perfil
public class CarerController {
    private final CarerService carerService;



    @GetMapping
    public List<Carer> list() {
        return carerService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Carer create(@RequestBody Carer carer) {
        return carerService.create(carer);
    }

    @GetMapping("/{id}")
    public Carer get(@PathVariable Integer id){
        return carerService.findById(id);
    }

    @PutMapping("/{id}")
    public Carer update(@PathVariable Integer id, @RequestBody Carer carer){
        return carerService.update(id, carer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        carerService.delete(id);
    }


}

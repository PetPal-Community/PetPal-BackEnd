package com.ingsw.petpal.controller;

import com.ingsw.petpal.model.entity.Services;
import com.ingsw.petpal.service.ServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/services")

public class ServicesController {
    private final ServicesService servicesService;


    @GetMapping
    public List<Services> list() {
        return servicesService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Services create(@RequestBody Services services) {
        return servicesService.create(services);
    }

    @GetMapping("/{id}")
    public Services get(@PathVariable Integer id){
        return servicesService.findById(id);
    }

    @PutMapping("/{id}")
    public Services update(@PathVariable Integer id, @RequestBody Services services){
        return servicesService.update(id, services);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        servicesService.delete(id);
    }


}

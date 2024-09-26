package com.ingsw.petpal.controller;

import com.ingsw.petpal.model.entity.Community;
import com.ingsw.petpal.service.ComunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comunity")

public class ComunityController {
    private final ComunityService comunityService;


    @GetMapping
    public List<Community> list(){ return comunityService.findAll(); }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Community create(@RequestBody Community community) {return comunityService.create(community);}

    @GetMapping("/{id}")
    public Community get(@PathVariable Integer id){
        return comunityService.findById(id);
    }

    @PutMapping("/{id}")
    public Community update(@PathVariable Integer id, @RequestBody Community comunity){

        return comunityService.update(id, comunity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){ comunityService.delete(id); }



}
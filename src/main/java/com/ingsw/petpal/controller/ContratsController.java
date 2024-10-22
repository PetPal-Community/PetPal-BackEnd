package com.ingsw.petpal.controller;

import com.ingsw.petpal.dto.ContratoDetailsDTO;
import com.ingsw.petpal.dto.ContratsCreateUpdateDTO;

import com.ingsw.petpal.service.ContratsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contrats")
@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER','CARER')")
public class ContratsController {
    private final ContratsService contratsService;


    @GetMapping
    public ResponseEntity<List<ContratoDetailsDTO>> list() {
        List<ContratoDetailsDTO> contrats = contratsService.findAll();

        return new ResponseEntity<List<ContratoDetailsDTO>>(contrats, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratoDetailsDTO> get(@PathVariable("id") Integer id){
        ContratoDetailsDTO contrato = contratsService.findById(id);
        return new ResponseEntity<ContratoDetailsDTO>(contrato,HttpStatus.OK);
    }

    @PostMapping
    public  ResponseEntity<ContratoDetailsDTO> createContrat(@Valid @RequestBody ContratsCreateUpdateDTO contratsfromDTO) {
        ContratoDetailsDTO contratCreated = contratsService.create(contratsfromDTO);
        return new ResponseEntity<ContratoDetailsDTO>(contratCreated,HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ContratoDetailsDTO> updateContrat(@PathVariable Integer id, @Valid @RequestBody ContratsCreateUpdateDTO contratsFromDTO) {
        ContratoDetailsDTO updateContrats = contratsService.update(id,contratsFromDTO);
        return new ResponseEntity<>(updateContrats,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){

        contratsService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
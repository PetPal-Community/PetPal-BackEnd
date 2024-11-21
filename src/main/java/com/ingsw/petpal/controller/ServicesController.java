package com.ingsw.petpal.controller;


import com.ingsw.petpal.dto.ServicesDTO;
import com.ingsw.petpal.dto.ServicesDetailsDTO;
import com.ingsw.petpal.service.ServicesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/services")
@PreAuthorize("hasAnyRole('ADMIN','CARER','CUSTOMER')")
public class ServicesController {
    private final ServicesService servicesService;



    @GetMapping
    public ResponseEntity<List<ServicesDetailsDTO>> getAllServices() {
        List<ServicesDetailsDTO> services = servicesService.getAll();
        return new ResponseEntity<List<ServicesDetailsDTO>>(services, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<ServicesDetailsDTO>> getServicesByCuidador(@PathVariable("id") Integer id) {
        List<ServicesDetailsDTO> services = servicesService.getServicesporCuidador(id);
        return new ResponseEntity<List<ServicesDetailsDTO>>(services, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicesDetailsDTO> getServiceById(@PathVariable("id") Integer id){
        ServicesDetailsDTO service = servicesService.findById(id);
        return new ResponseEntity<ServicesDetailsDTO>(service, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ServicesDetailsDTO> createService(@Valid @RequestBody ServicesDTO servicesDTO) {
        ServicesDetailsDTO serviceCreado = servicesService.create(servicesDTO);
        return new ResponseEntity<ServicesDetailsDTO>(serviceCreado, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ServicesDetailsDTO> updateService(@PathVariable Integer id,@Valid @RequestBody ServicesDTO servicesDTO) {
        ServicesDetailsDTO updateService = servicesService.update(id,servicesDTO);
        return new ResponseEntity<>(updateService, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Integer id){
        servicesService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

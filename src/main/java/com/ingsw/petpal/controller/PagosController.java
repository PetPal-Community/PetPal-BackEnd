package com.ingsw.petpal.controller;

import com.ingsw.petpal.dto.PagosDTO;
import com.ingsw.petpal.service.PagosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pagos")
@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER', 'CARER')")
public class PagosController {

    private final PagosService pagosService;

    @GetMapping
    public ResponseEntity<List<PagosDTO>> getAllPagos() {
        List<PagosDTO> pagosList = pagosService.getAll();
        return new ResponseEntity<>(pagosList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagosDTO> getPagoById(@PathVariable("id") Integer id) {
        PagosDTO pagosDTO = pagosService.findById(id);
        return new ResponseEntity<>(pagosDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PagosDTO> createPago(@Valid @RequestBody PagosDTO pagosDTO) {
        PagosDTO createdPago = pagosService.create(pagosDTO);
        return new ResponseEntity<>(createdPago, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagosDTO> updatePago(@PathVariable("id") Integer id, @Valid @RequestBody PagosDTO pagosDTO) {
        PagosDTO updatedPago = pagosService.update(id, pagosDTO);
        return new ResponseEntity<>(updatedPago, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePago(@PathVariable("id") Integer id) {
        pagosService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
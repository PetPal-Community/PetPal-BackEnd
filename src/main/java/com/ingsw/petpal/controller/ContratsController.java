package com.ingsw.petpal.controller;

import com.ingsw.petpal.dto.ContratoDetailsDTO;
import com.ingsw.petpal.dto.ContratoReporteDTO;
import com.ingsw.petpal.dto.ContratsCreateUpdateDTO;

import com.ingsw.petpal.service.ContratsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contrats")
@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER','CARER')")
public class ContratsController {
    private final ContratsService contratsService;

    @GetMapping("/reporte/{cuidadorId}")
    public ResponseEntity<ContratoReporteDTO> getReport(@PathVariable Integer cuidadorId,
                                                                      @RequestParam int mes,
                                                                      @RequestParam int anio) {
        // Obtener el reporte mensual
        System.out.println("Recibiendo solicitud para el cuidador con ID: " + cuidadorId + ", mes: " + mes + ", año: " + anio);
        ContratoReporteDTO report = contratsService.getContractReportByMonth(cuidadorId, mes, anio);
        return ResponseEntity.ok(report);
    }

    @GetMapping
    public ResponseEntity<List<ContratoDetailsDTO>> list() {
        List<ContratoDetailsDTO> contrats = contratsService.findAll();

        return new ResponseEntity<List<ContratoDetailsDTO>>(contrats, HttpStatus.OK);
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<List<ContratoDetailsDTO>> listbyCuidador(@PathVariable("id") Integer id) {
        List<ContratoDetailsDTO> contrats = contratsService.findByCarerId(id);

        return new ResponseEntity<List<ContratoDetailsDTO>>(contrats, HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<ContratoDetailsDTO>> listByCustomer(@PathVariable("id") Integer id) {
        List<ContratoDetailsDTO> contrats = contratsService.findByUserId(id);

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

    @PatchMapping("/{id}/estado")
    public ResponseEntity<ContratoDetailsDTO> updateEstado(@PathVariable Integer id, @RequestBody String estado) {
        ContratoDetailsDTO updatedContrato = contratsService.updateEstado(id, estado);
        return ResponseEntity.ok(updatedContrato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){

        contratsService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
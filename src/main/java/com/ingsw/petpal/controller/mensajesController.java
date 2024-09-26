package com.ingsw.petpal.controller;


import com.ingsw.petpal.dto.MensajeDetailsDTO;
import com.ingsw.petpal.dto.MensajesCreateUpdateDTO;
import com.ingsw.petpal.model.entity.Mensajes;


import com.ingsw.petpal.service.MensajesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/mensajes")
public class mensajesController {
    private final MensajesService mensajesService;

    @GetMapping
    public ResponseEntity<List<MensajeDetailsDTO>> getAllMensajes() {
        List<MensajeDetailsDTO> mensajes = mensajesService.findAll();
        return new ResponseEntity<List<MensajeDetailsDTO>>(mensajes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MensajeDetailsDTO> get(@PathVariable("id") Integer id){
        MensajeDetailsDTO mensaje = mensajesService.findById(id);
        return new ResponseEntity<MensajeDetailsDTO>(mensaje, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MensajeDetailsDTO> create(@Valid @RequestBody MensajesCreateUpdateDTO mensajesFromDTO) {
        MensajeDetailsDTO mensajeCreado = mensajesService.create(mensajesFromDTO);
        return new ResponseEntity<MensajeDetailsDTO>(mensajeCreado, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<MensajeDetailsDTO> update(@PathVariable Integer id,@Valid @RequestBody MensajesCreateUpdateDTO mensajesFromDTO){
        MensajeDetailsDTO updateMensaje = mensajesService.update(id,mensajesFromDTO);
        return new ResponseEntity<>(updateMensaje, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        mensajesService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

package com.ingsw.petpal.controller;


import com.ingsw.petpal.dto.UsuarioDTO;
import com.ingsw.petpal.model.entity.User;
import com.ingsw.petpal.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
@PreAuthorize("hasAnyRole('ADMIN','CUSTOME')")
public class UserController {

    private final UserService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.getAll();
        return new ResponseEntity<List<UsuarioDTO>>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable("id") Integer id) {
        UsuarioDTO usuario = usuarioService.findById(id);
        return new ResponseEntity<UsuarioDTO>(usuario, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<UsuarioDTO> createUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO createdUsuario = usuarioService.create(usuarioDTO);
        return new ResponseEntity<UsuarioDTO>(createdUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable("id") Integer id, @Valid @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO updatedUsuario = usuarioService.update(id, usuarioDTO);
        return new ResponseEntity<UsuarioDTO>(updatedUsuario, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioDTO> deleteUsuario(@PathVariable("id") Integer id) {
        usuarioService.delete(id);
        return new ResponseEntity<UsuarioDTO>(HttpStatus.NO_CONTENT);
    }
}
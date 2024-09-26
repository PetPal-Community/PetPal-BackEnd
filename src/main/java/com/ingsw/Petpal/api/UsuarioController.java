package com.ingsw.Petpal.api;

import com.ingsw.Petpal.dto.UsuarioDTO;
import com.ingsw.Petpal.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

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

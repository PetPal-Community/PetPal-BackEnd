package com.ingsw.petpal.controller;

import com.ingsw.petpal.model.entity.ComunidadUser;
import com.ingsw.petpal.model.entity.User;
import com.ingsw.petpal.service.ComunidadUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios-comunidad")
@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
public class ComunidadUserController {

    private final ComunidadUserService comunidadUserService;

    // http://localhost:8080/usuarios-comunidad/1/usuarios
    @GetMapping("/{comunidadId}/usuarios")
    public ResponseEntity<List<User>> getUsuariosInComunidad(@PathVariable Integer comunidadId) {
        List<User> usuarios = comunidadUserService.getUsuariosInComunidad(comunidadId);
        return ResponseEntity.ok(usuarios);
    }
    // http://localhost:8080/usuarios-comunidad/1/add-user?usuarioId=1
    @PostMapping("/{comunidadId}/add-user")
    public ResponseEntity<ComunidadUser> addUsuarioToComunidad(@PathVariable Integer comunidadId,
                                                         @RequestParam Integer usuarioId) {
        ComunidadUser comunidadUser = comunidadUserService.addUsuarioToComunidad(usuarioId, comunidadId);
        return new ResponseEntity<>(comunidadUser, HttpStatus.CREATED);
    }

    // http://localhost:8080/usuarios-comunidad/1/remove-user?usuarioId=1
    @DeleteMapping("/{comunidadId}/remove-user/{usuarioId}")
    public ResponseEntity<Void> removeUsuarioFromComunidad(@PathVariable Integer comunidadId,
                                                     @PathVariable Integer usuarioId) {
        comunidadUserService.removeUsuarioFromComunidad(usuarioId, comunidadId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

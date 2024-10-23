package com.ingsw.petpal.controller;

import com.ingsw.petpal.dto.UserGeneralProfileDTO;
import com.ingsw.petpal.service.UserGeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER','CARER')")
public class UserGeneralController {
    private final UserGeneralService userGeneralService;

    @PutMapping("/{id}")
    public ResponseEntity<UserGeneralProfileDTO> actualizarPerfil(@PathVariable Integer id, @RequestBody UserGeneralProfileDTO userGeneralProfileDTO) {
        UserGeneralProfileDTO userProfile = userGeneralService.updateUserGeneral(id, userGeneralProfileDTO);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGeneralProfileDTO> obtenerPerfil(@PathVariable Integer id) {
        UserGeneralProfileDTO userGeneralProfileDTO = userGeneralService.getUserGeneralById(id);
        return new ResponseEntity<>(userGeneralProfileDTO, HttpStatus.OK);
    }
}

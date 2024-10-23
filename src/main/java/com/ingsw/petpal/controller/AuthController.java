package com.ingsw.petpal.controller;

import com.ingsw.petpal.dto.AuthResponseDTO;
import com.ingsw.petpal.dto.LoginDTO;
import com.ingsw.petpal.dto.UserGeneralProfileDTO;
import com.ingsw.petpal.dto.UserGeneralRegistration;
import com.ingsw.petpal.service.UserGeneralService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authenticacion")
public class AuthController {

    private final UserGeneralService userGeneralService;

    @PostMapping("/registrar/customer")
    public ResponseEntity<UserGeneralProfileDTO> registrarCustomer(@Valid @RequestBody UserGeneralRegistration userGeneralRegistration) {
        UserGeneralProfileDTO userGeneralProfileDTO = userGeneralService.registrarUser(userGeneralRegistration);
        return new ResponseEntity<>(userGeneralProfileDTO, HttpStatus.CREATED);
    }

    @PostMapping("/registrar/carer")
    public ResponseEntity<UserGeneralProfileDTO> registrarCarer(@Valid @RequestBody UserGeneralRegistration userGeneralRegistration) {
        UserGeneralProfileDTO userGeneralProfileDTO = userGeneralService.registrarCarer(userGeneralRegistration);
        return new ResponseEntity<>(userGeneralProfileDTO, HttpStatus.CREATED);
    }

    @PostMapping("/registrar/admin")
    public ResponseEntity<UserGeneralProfileDTO> registrarAdmin(@Valid @RequestBody UserGeneralRegistration userGeneralRegistration){
        UserGeneralProfileDTO userGeneralProfileDTO = userGeneralService.registrarAdmin(userGeneralRegistration);
        return new ResponseEntity<>(userGeneralProfileDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        AuthResponseDTO authResponseDTO = userGeneralService.loginUser(loginDTO);
        return new ResponseEntity<>(authResponseDTO, HttpStatus.OK);
    }


}

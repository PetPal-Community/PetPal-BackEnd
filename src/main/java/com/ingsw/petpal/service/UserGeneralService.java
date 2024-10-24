package com.ingsw.petpal.service;

import com.ingsw.petpal.dto.AuthResponseDTO;
import com.ingsw.petpal.dto.LoginDTO;
import com.ingsw.petpal.dto.UserGeneralProfileDTO;
import com.ingsw.petpal.dto.UserGeneralRegistration;

public interface UserGeneralService {
    //Registrar user
    UserGeneralProfileDTO registrarUser(UserGeneralRegistration userGeneralRegistration);

    // Registrar carer
    UserGeneralProfileDTO registrarCarer(UserGeneralRegistration userGeneralRegistration);

    // Registrar Admin
    UserGeneralProfileDTO registrarAdmin(UserGeneralRegistration userGeneralRegistration);

    // Actualizar perfil de userGeneral
    UserGeneralProfileDTO updateUserGeneral(Integer id, UserGeneralProfileDTO userGeneralProfileDTO);

    // Obtener perfil de User
    UserGeneralProfileDTO getUserGeneralById(Integer id);

    //Obtener perfil de User por correo
    //UserGeneralProfileDTO getUserGeneralByGmail(String gmail);

    // autenticar usuario ()
    AuthResponseDTO loginUser(LoginDTO loginDTO);
}

package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.dto.AuthResponseDTO;
import com.ingsw.petpal.dto.LoginDTO;
import com.ingsw.petpal.dto.UserGeneralProfileDTO;
import com.ingsw.petpal.dto.UserGeneralRegistration;
import com.ingsw.petpal.exception.ResourceNotFoundException;
import com.ingsw.petpal.exception.RolNotFoundException;
import com.ingsw.petpal.mapper.UserGeneralMapper;
import com.ingsw.petpal.model.entity.Carer;
import com.ingsw.petpal.model.entity.Role;
import com.ingsw.petpal.model.entity.User;
import com.ingsw.petpal.model.entity.UserGeneral;
import com.ingsw.petpal.model.entity.enums.ERoles;
import com.ingsw.petpal.repository.CarerRepository;
import com.ingsw.petpal.repository.RoleRepository;
import com.ingsw.petpal.repository.UserGeneralRepository;
import com.ingsw.petpal.repository.UserRepository;
import com.ingsw.petpal.security.TokenProvider;
import com.ingsw.petpal.security.UserPrincipal;
import com.ingsw.petpal.service.UserGeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserGeneralServiceImpl implements UserGeneralService {
    private final UserGeneralRepository userGeneralRepository;
    private final RoleRepository roleRepository;
    private final CarerRepository carerRepository;
    private final UserRepository userRepository;
    private final UserGeneralMapper userGeneralMapper;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager; // Necesario para la autenticación
    private final TokenProvider tokenProvider; // Necesario para la creación de tokens JWT

    private UserGeneralProfileDTO registrarUserGeneralWRole(UserGeneralRegistration userGeneralRegistration, ERoles rol) {
        // Verificaciones si existe
        //Email
        boolean existsByEmail = userGeneralRepository.existsByEmail(userGeneralRegistration.getEmail());
        boolean existsASUser = userRepository.existsByNombreAndApellido(userGeneralRegistration.getNombre(), userGeneralRegistration.getApellido());
        boolean existsASCarer = carerRepository.existsByNombreAndApellido(userGeneralRegistration.getNombre(), userGeneralRegistration.getApellido());

        if (existsByEmail){
            throw new IllegalArgumentException("El email ya existe");
        }
        if (existsASUser){
            throw new IllegalArgumentException("El usuario ya existe con el mismo nombre y apellido");
        }

        if (existsASCarer){
            throw new IllegalArgumentException("El cuidador ya existe con el mismo nombre y apellido");
        }

        Role role = roleRepository.findByName(rol)
                .orElseThrow(() -> new RolNotFoundException("Error: El rol no existe"));

        userGeneralRegistration.setContrasena(passwordEncoder.encode(userGeneralRegistration.getContrasena()));
        UserGeneral userGeneral = userGeneralMapper.toEntity(userGeneralRegistration);
        userGeneral.setRole(role);

        if(rol == ERoles.CUSTOMER){
        User user = new User();
        user.setNombre(userGeneralRegistration.getNombre());
        user.setApellido(userGeneralRegistration.getApellido());
        user.setTelefono(userGeneralRegistration.getTelefono());
        user.setPicRuta(userGeneralRegistration.getPicRuta());
        user.setUser(userGeneral);
        userGeneral.setUsuario(user);
        }
        else if(rol == ERoles.CARER){
            Carer carer = new Carer();
            carer.setNombre(userGeneralRegistration.getNombre());
            carer.setApellido(userGeneralRegistration.getApellido());
            carer.setTelefono(userGeneralRegistration.getTelefono());
            carer.setPicRuta(userGeneralRegistration.getPicRuta());
            carer.setUser(userGeneral);
            userGeneral.setCarer(carer);
        }
        UserGeneral savedUserGeneral = userGeneralRepository.save(userGeneral);
        return userGeneralMapper.toDTO(savedUserGeneral);
    }

    @Transactional
    @Override
    public UserGeneralProfileDTO registrarUser(UserGeneralRegistration userGeneralRegistration){
        return registrarUserGeneralWRole(userGeneralRegistration, ERoles.CUSTOMER);
    }

    @Transactional
    @Override
    public UserGeneralProfileDTO registrarCarer(UserGeneralRegistration userGeneralRegistration){
        return registrarUserGeneralWRole(userGeneralRegistration, ERoles.CARER);
    }

    @Transactional
    @Override
    public UserGeneralProfileDTO registrarAdmin(UserGeneralRegistration userGeneralRegistration){
        return registrarUserGeneralWRole(userGeneralRegistration, ERoles.ADMIN);
    }

    @Transactional
    @Override
    public UserGeneralProfileDTO updateUserGeneral(Integer id, UserGeneralProfileDTO userGeneralProfileDTO ){
        UserGeneral userGeneral = userGeneralRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User no encontrado"));

        boolean existsAsCustomer = userRepository.existsByNombreAndApellidoAndUserIdNot(
                userGeneralProfileDTO.getNombre(), userGeneralProfileDTO.getApellido(), userGeneralProfileDTO.getId());
        boolean existsAsCarer = carerRepository.existsByNombreAndApellidoAndUserIdNot(userGeneralProfileDTO.getNombre(), userGeneralProfileDTO.getApellido(), userGeneral.getId());

        if(existsAsCustomer){
            throw new IllegalArgumentException("El usuario ya existe con el mismo nomre y apelido");
        }

        if(existsAsCarer){
            throw new IllegalArgumentException("Ya existe un cuidador con el mismo nombre y apellido");
        }

        if(userGeneral.getUsuario()!= null){
            userGeneral.getUsuario().setNombre(userGeneralProfileDTO.getNombre());
            userGeneral.getUsuario().setApellido(userGeneralProfileDTO.getApellido());
            userGeneral.getUsuario().setTelefono(userGeneralProfileDTO.getTelefono());
            userGeneral.getUsuario().setPicRuta(userGeneralProfileDTO.getPicRuta());
        }

        if(userGeneral.getCarer()!= null){
            userGeneral.getCarer().setNombre(userGeneralProfileDTO.getNombre());
            userGeneral.getCarer().setApellido(userGeneralProfileDTO.getApellido());
            userGeneral.getCarer().setTelefono(userGeneralProfileDTO.getTelefono());
            userGeneral.getCarer().setPicRuta(userGeneralProfileDTO.getPicRuta());
        }
        UserGeneral updatedUserGeneral = userGeneralRepository.save(userGeneral);
        return userGeneralMapper.toDTO(updatedUserGeneral);
    }

    @Transactional(readOnly = true)
    @Override
    public UserGeneralProfileDTO getUserGeneralById(Integer id){
        UserGeneral userGeneral = userGeneralRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User no encontrado"));
        return userGeneralMapper.toDTO(userGeneral);
    }

    /*@Transactional(readOnly = true)
    @Override
    public UserGeneralProfileDTO getUserGeneralByGmail(String gmail){
        UserGeneral userGeneral = userGeneralRepository.findByEmail(gmail).orElseThrow(() -> new ResourceNotFoundException("User no encontrado"));
        return userGeneralMapper.toDTO(userGeneral);
    }¨*/

    @Override
    public AuthResponseDTO loginUser(LoginDTO loginDTO) {

        // Autenticar al usuario utilizando AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getContrasena())
        );

        // Una vez autenticado, el objeto autentication contiene la informacion del usuario autenticado
        UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();
        UserGeneral userGeneral = userPrincipal.getUserGeneral();

        // Verificar si es un administrador
        boolean isAdmin = userGeneral.getRole().getName().equals(ERoles.ADMIN);

        //String token = "abc123";
        // Generar el token JWT usando el TokenProvider
        String token = tokenProvider.createAccessToken(authentication);

        // Generar la respuesta de autenticación, con el rol correspondiente
        AuthResponseDTO responseDTO = userGeneralMapper.toAuthResponseDTO(userGeneral, token);

        // Retornar la respuesta
        return responseDTO;
    }
}

package com.ingsw.petpal.security;

import com.ingsw.petpal.model.entity.UserGeneral;
import com.ingsw.petpal.repository.UserGeneralRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

// Maneja la logica de negocio del inicio de sesion
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserGeneralRepository userGeneralRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserGeneral userGeneral = userGeneralRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el email: " + email));

        // Rols para Spring SEcurity
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority( "ROLE_" + userGeneral.getRole().getName().name());

        return new UserPrincipal(
                userGeneral.getId(),
                userGeneral.getEmail(),
                userGeneral.getContrasena(),
                Collections.singleton(grantedAuthority),
                userGeneral
        );
    }
}

package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.exception.ResourceNotFoundException;
import com.ingsw.petpal.model.entity.Community;
import com.ingsw.petpal.model.entity.ComunidadUser;
import com.ingsw.petpal.model.entity.User;
import com.ingsw.petpal.repository.ComunidadUserRepository;
import com.ingsw.petpal.repository.ComunityRepository;
import com.ingsw.petpal.service.ComunidadUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ComunidadUserServiceImpl implements ComunidadUserService {

    private final ComunityRepository comunityRepository;
    private final ComunidadUserRepository comunidadUserRepository;

    @Override
    public ComunidadUser addUsuarioToComunidad(Integer usuarioId, Integer comunidadId) {
        LocalDateTime FechaDeUnion = LocalDateTime.now();
        comunidadUserRepository.addUsuarioToComunidad(usuarioId, comunidadId, FechaDeUnion);

        ComunidadUser comunidadUser = new ComunidadUser();
        comunidadUser.setUsuario(usuarioId);
        comunidadUser.setComunidad(comunidadId);
        comunidadUser.setFechaDeUnion(FechaDeUnion);
        return comunidadUser;
    }

    @Override
    public void removeUsuarioFromComunidad(Integer usuarioId, Integer comunidadId) {
        comunidadUserRepository.deleteByUsuarioAndComunidad(usuarioId, comunidadId);
    }

    @Override
    public List<User> getUsuariosInComunidad(Integer comunidadId) {
        Community community = comunityRepository.findById(comunidadId).orElseThrow(() -> new ResourceNotFoundException("Comunidad no encontrada"));
        return comunidadUserRepository.findUsuariosByComunidad(community);

    }
}

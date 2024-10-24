package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.dto.UsuarioDTO;
import com.ingsw.petpal.exception.BadRequestException;
import com.ingsw.petpal.exception.ResourceNotFoundException;
import com.ingsw.petpal.mapper.UsuarioMapper;
import com.ingsw.petpal.model.entity.User;
import com.ingsw.petpal.repository.UserRepository;
import com.ingsw.petpal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Transactional(readOnly = true)
    @Override
    public List<UsuarioDTO> getAll() {
        List<User> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuarioMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public UsuarioDTO findById(Integer id) {
        User usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        return usuarioMapper.toDTO(usuario);
    }

    @Transactional
    @Override
    public UsuarioDTO create(UsuarioDTO usuarioDTO) {
        // Validación de duplicado por email o cualquier otro campo único
        /*usuarioRepository.findByEmail(usuarioDTO.getEmail())
                .ifPresent(usuario -> {
                    throw new BadRequestException("El usuario ya existe con el mismo email");
                });*/

        // Mapear DTO a entidad
        User usuario = usuarioMapper.toEntity(usuarioDTO);

        // Guardar el nuevo usuario
        usuario = usuarioRepository.save(usuario);

        // Retornar el DTO del usuario creado
        return usuarioMapper.toDTO(usuario);
    }

    @Transactional
    @Override
    public UsuarioDTO update(Integer id, UsuarioDTO updatedUsuarioDTO) {
        // Buscar el usuario existente
        User usuarioFromDb = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        // Validar que no haya duplicado de email con otro usuario
        /*usuarioRepository.findByEmail(updatedUsuarioDTO.getEmail())
                .filter(existingUsuario -> !existingUsuario.getId().equals(id))
                .ifPresent(existingUsuario -> {
                    throw new BadRequestException("El usuario ya existe con el mismo email");
                });*/

        // Actualizar los campos del usuario
        usuarioFromDb.setNombre(updatedUsuarioDTO.getNombre());
        usuarioFromDb.setApellido(updatedUsuarioDTO.getApellido());
        //usuarioFromDb.setEmail(updatedUsuarioDTO.getEmail());
        usuarioFromDb.setTelefono(updatedUsuarioDTO.getTelefono());

        // Guardar el usuario actualizado
        usuarioFromDb = usuarioRepository.save(usuarioFromDb);

        // Retornar el DTO del usuario actualizado
        return usuarioMapper.toDTO(usuarioFromDb);
    }
    /*
    @Transactional
    @Override
    public UsuarioDTO create(UsuarioDTO usuarioDTO) {
        usuarioRepository.findByNombreAndApPaterno(usuarioDTO.getNombre(), usuarioDTO.getApPaterno())
                .ifPresent(usuario -> {
                    throw new BadRequestException("El usuario ya existe con el mismo npmbre y apellido paterno");
                });

        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario.setApMaterno(usuarioDTO.getApMaterno());
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }

    @Transactional
    @Override
    public UsuarioDTO update(Integer id, UsuarioDTO updatedUsuarioDTO) {
        Usuario usuarioFromDb = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        usuarioRepository.findByNombreAndApPaterno(updatedUsuarioDTO.getNombre(), updatedUsuarioDTO.getApPaterno())
                .filter(existingUsuario -> !existingUsuario.getId().equals(id))
                .ifPresent(existingUsuario -> {throw new BadRequestException("El usuario ya existe con el mismo nombre y apellido paterno");});

        usuarioFromDb.setApMaterno(updatedUsuarioDTO.getApMaterno());

        usuarioFromDb = usuarioRepository.save(usuarioFromDb);

        return usuarioMapper.toDTO(usuarioFromDb);
    }

    */
    @Transactional
    @Override
    public void delete(Integer id) {
        User usuarioFromDb = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        usuarioRepository.delete(usuarioFromDb);
    }
}
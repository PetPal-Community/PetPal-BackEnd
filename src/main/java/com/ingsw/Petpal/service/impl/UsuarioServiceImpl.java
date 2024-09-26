    package com.ingsw.Petpal.service.impl;

    import com.ingsw.Petpal.dto.UsuarioDTO;
    import com.ingsw.Petpal.exception.BadRequestException;
    import com.ingsw.Petpal.exception.ResourceNotFoundException;
    import com.ingsw.Petpal.mapper.UsuarioMapper;
    import com.ingsw.Petpal.model.entity.Usuario;
    import com.ingsw.Petpal.repository.UsuarioRepository;
    import com.ingsw.Petpal.service.UsuarioService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import java.util.List;

    @Service
    @RequiredArgsConstructor
    public class UsuarioServiceImpl implements UsuarioService {

        private final UsuarioRepository usuarioRepository;
        private final UsuarioMapper usuarioMapper;

        @Transactional(readOnly = true)
        @Override
        public List<UsuarioDTO> getAll() {
            List<Usuario> usuarios = usuarioRepository.findAll();
            return usuarios.stream()
                    .map(usuarioMapper::toDTO)
                    .toList();
        }

        @Transactional(readOnly = true)
        @Override
        public UsuarioDTO findById(Integer id) {
            Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
            return usuarioMapper.toDTO(usuario);
        }

        @Transactional
        @Override
        public UsuarioDTO create(UsuarioDTO usuarioDTO) {
            // Validación de duplicado por email o cualquier otro campo único
            usuarioRepository.findByEmail(usuarioDTO.getEmail())
                    .ifPresent(usuario -> {
                        throw new BadRequestException("El usuario ya existe con el mismo email");
                    });

            // Mapear DTO a entidad
            Usuario usuario = usuarioMapper.toEntity(usuarioDTO);

            // Guardar el nuevo usuario
            usuario = usuarioRepository.save(usuario);

            // Retornar el DTO del usuario creado
            return usuarioMapper.toDTO(usuario);
        }

        @Transactional
        @Override
        public UsuarioDTO update(Integer id, UsuarioDTO updatedUsuarioDTO) {
            // Buscar el usuario existente
            Usuario usuarioFromDb = usuarioRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

            // Validar que no haya duplicado de email con otro usuario
            usuarioRepository.findByEmail(updatedUsuarioDTO.getEmail())
                    .filter(existingUsuario -> !existingUsuario.getId().equals(id))
                    .ifPresent(existingUsuario -> {
                        throw new BadRequestException("El usuario ya existe con el mismo email");
                    });

            // Actualizar los campos del usuario
            usuarioFromDb.setNombre(updatedUsuarioDTO.getNombre());
            usuarioFromDb.setApellido(updatedUsuarioDTO.getApellido());
            usuarioFromDb.setEmail(updatedUsuarioDTO.getEmail());
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
            Usuario usuarioFromDb = usuarioRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
            usuarioRepository.delete(usuarioFromDb);
        }
    }

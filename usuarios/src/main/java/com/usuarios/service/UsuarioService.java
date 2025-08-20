package com.usuarios.service;

import com.usuarios.dto.RolDto;
import com.usuarios.dto.UsuarioDto;
import com.usuarios.entity.Usuarios;
import com.usuarios.exception.RolNoExisteException;
import com.usuarios.mapper.UsuarioMapper;
import com.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // 👈 Importar
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final String ROL_SERVICE_URL = "http://localhost:8091/api/roles/";

    public List<UsuarioDto> obtenerTodos() {
        List<Usuarios> usuariosList = usuarioRepository.findAll();
        return usuariosList.stream().map(usuario -> {
            RolDto rolDto = null;
            try {
                if (usuario.getRolid() != null) {
                    rolDto = restTemplate.getForObject(ROL_SERVICE_URL + usuario.getRolid(), RolDto.class);
                }
            } catch (Exception e) {
                System.err.println("Error al obtener el rol del usuario con ID " + usuario.getRolid());
            }
            return usuarioMapper.toDto(usuario, rolDto);
        }).collect(Collectors.toList());
    }

    public UsuarioDto obtenerPorId(Long id) {
        Optional<Usuarios> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuarios usuario = optionalUsuario.get();
            RolDto rolDto = null;
            if (usuario.getRolid() != null) {
                rolDto = restTemplate.getForObject(ROL_SERVICE_URL + usuario.getRolid(), RolDto.class);
            }
            return usuarioMapper.toDto(usuario, rolDto);
        }
        return null;
    }

    public UsuarioDto crearUsuarioConRol(UsuarioDto usuarioDto) {
        // Validación de username y DNI duplicados
        if (usuarioRepository.existsByUsername(usuarioDto.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya está registrado");
        }

        if (usuarioRepository.existsByDni(usuarioDto.getDni())) {
            throw new RuntimeException("El DNI ya está registrado");
        }

        // Validar que se haya enviado el rol correctamente
        if (usuarioDto.getRolDto() == null || usuarioDto.getRolDto().getRolid() == null) {
            throw new RuntimeException("Debes asignar un rol existente al usuario");
        }

        // Validar que el rol exista llamando al microservicio
        RolDto rolDto;
        try {
            rolDto = restTemplate.getForObject(ROL_SERVICE_URL + usuarioDto.getRolDto().getRolid(), RolDto.class);
        } catch (Exception e) {
            throw new RuntimeException("El rol con ID " + usuarioDto.getRolDto().getRolid() + " no existe");
        }

        // Convertir DTO a entidad y asignar el ID del rol
        Usuarios nuevoUsuario = usuarioMapper.toEntity(usuarioDto);
        nuevoUsuario.setRolid(rolDto.getRolid());

        // ✅ Encriptar contraseña
        nuevoUsuario.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));

        // Guardar usuario
        Usuarios guardado = usuarioRepository.save(nuevoUsuario);

        // Devolver DTO con rol incluido
        return usuarioMapper.toDto(guardado, rolDto);
    }

    public UsuarioDto actualizarUsuario(Long id, UsuarioDto usuarioDto) {
        Optional<Usuarios> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isEmpty()) {
            throw new RuntimeException("El usuario con ID " + id + " no existe");
        }

        Usuarios usuarioExistente = optionalUsuario.get();
        RolDto rolDto = null;

        // Verificamos si se está asignando un nuevo rol
        if (usuarioDto.getRolDto() != null && usuarioDto.getRolDto().getRolid() != null) {
            try {
                rolDto = restTemplate.getForObject(
                        ROL_SERVICE_URL + usuarioDto.getRolDto().getRolid(), RolDto.class);
                if (rolDto == null) {
                    throw new RolNoExisteException("El rol con ID " + usuarioDto.getRolDto().getRolid() + " no existe");
                }
                usuarioExistente.setRolid(rolDto.getRolid());
            } catch (Exception e) {
                throw new RolNoExisteException("El rol con ID " + usuarioDto.getRolDto().getRolid() + " no existe");
            }
        }

        usuarioExistente.setNombre(usuarioDto.getNombre());
        usuarioExistente.setApellidos(usuarioDto.getApellidos());
        usuarioExistente.setDni(usuarioDto.getDni());
        usuarioExistente.setUsername(usuarioDto.getUsername());

        // ✅ Encriptar la nueva contraseña (si viene en el DTO)
        usuarioExistente.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));

        Usuarios actualizado = usuarioRepository.save(usuarioExistente);
        return usuarioMapper.toDto(actualizado, rolDto);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}

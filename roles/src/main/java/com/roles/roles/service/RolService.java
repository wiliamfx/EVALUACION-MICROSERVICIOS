package com.roles.roles.service;

import com.roles.roles.dto.RolRequestDto;
import com.roles.roles.dto.RolResponseDto;
import com.roles.roles.entity.Rol;
import com.roles.roles.repository.Rolrepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RolService {

    @Autowired
    private Rolrepository rolRepository;

    public RolResponseDto obtenerPorNombre(String nombre) {
        Rol rol = rolRepository.findByRolnombre(nombre)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        return new RolResponseDto(rol.getRolid(), rol.getRolnombre());
    }


    public Rol buscarPorNombre(String nombre) {
        return rolRepository.findByRolnombre(nombre).orElse(null);
    }


    // Crear un rol
    public RolResponseDto crearRol(RolRequestDto rolRequestDto) {
        Rol rol = new Rol();
        rol.setRolnombre(rolRequestDto.getRolnombre());
        Rol savedRol = rolRepository.save(rol);
        return new RolResponseDto(savedRol.getRolid(), savedRol.getRolnombre());
    }

    // Obtener todos los roles
    public List<RolResponseDto> obtenerTodosRoles() {
        List<Rol> roles = rolRepository.findAll();
        return roles.stream()
                .map(rol -> new RolResponseDto(rol.getRolid(), rol.getRolnombre()))
                .collect(Collectors.toList()); // Usamos Collectors.toList() para recolectar los resultados en una lista
    }


    // Obtener un rol por ID
    public RolResponseDto obtenerRolPorId(Long id) {
        Optional<Rol> rol = rolRepository.findById(id);
        if (rol.isEmpty()) {
            throw new RuntimeException("Rol no encontrado");
        }
        Rol r = rol.get();
        return new RolResponseDto(r.getRolid(), r.getRolnombre());
    }

    // Actualizar un rol
    public RolResponseDto actualizarRol(Long id, RolRequestDto rolRequestDto) {
        Optional<Rol> rolOpt = rolRepository.findById(id);
        if (rolOpt.isEmpty()) {
            throw new RuntimeException("Rol no encontrado");
        }
        Rol rol = rolOpt.get();
        rol.setRolnombre(rolRequestDto.getRolnombre());
        rolRepository.save(rol);
        return new RolResponseDto(rol.getRolid(), rol.getRolnombre());
    }

    // Eliminar un rol
    public String eliminarRol(Long id) {
        if (!rolRepository.existsById(id)) {
            throw new RuntimeException("Rol no encontrado");
        }
        rolRepository.deleteById(id);
        return "Rol eliminado con éxito";
    }
}

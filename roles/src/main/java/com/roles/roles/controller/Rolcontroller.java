package com.roles.roles.controller;

import com.roles.roles.dto.RolRequestDto;
import com.roles.roles.dto.RolResponseDto;
import com.roles.roles.entity.Rol;
import com.roles.roles.repository.Rolrepository;
import com.roles.roles.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class Rolcontroller {

    @Autowired
    private RolService rolService;
    @Autowired
    private Rolrepository rolrepository;

    // Crear un rol
    @PostMapping("/crear")
    public ResponseEntity<RolResponseDto> crearRol(@RequestBody RolRequestDto rolRequestDto) {
        RolResponseDto rolCreado = rolService.crearRol(rolRequestDto);
        return new ResponseEntity<>(rolCreado, HttpStatus.CREATED);
    }

    // Obtener todos los roles
    @GetMapping("/listar")
    public List<RolResponseDto> listarRoles() {
        return rolService.obtenerTodosRoles();
    }

    // Obtener un rol por ID
    @GetMapping("/{id}")
    public ResponseEntity<RolResponseDto> obtenerRolPorId(@PathVariable Long id) {
        RolResponseDto rol = rolService.obtenerRolPorId(id);
        return new ResponseEntity<>(rol, HttpStatus.OK);
    }

    // Actualizar un rol
    @PutMapping("/{id}")
    public ResponseEntity<RolResponseDto> actualizarRol(@PathVariable Long id, @RequestBody RolRequestDto rolRequestDto) {
        RolResponseDto rolActualizado = rolService.actualizarRol(id, rolRequestDto);
        return new ResponseEntity<>(rolActualizado, HttpStatus.OK);
    }

    // Eliminar un rol
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRol(@PathVariable Long id) {
        String response = rolService.eliminarRol(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // Obtener rol por nombre
    @GetMapping("/name/{nombre}")
    public ResponseEntity<RolResponseDto> obtenerPorNombre(@PathVariable String nombre) {
        RolResponseDto rol = rolService.obtenerPorNombre(nombre);
        return new ResponseEntity<>(rol, HttpStatus.OK);
    }



}

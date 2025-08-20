package com.clientes.controller;

import com.clientes.dto.ClienteDto;
import com.clientes.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/crear")
    public ResponseEntity<ClienteDto> crear(@Valid @RequestBody ClienteDto clienteDto){
        return ResponseEntity.ok(clienteService.crearCliente(clienteDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> obtener(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.obtenerPorid(id));
    }
    @GetMapping("/listar")
    public ResponseEntity<List<ClienteDto>> listar(){
        return ResponseEntity.ok(clienteService.listarClientes());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ClienteDto> actualizar(@PathVariable Long id,@Valid @RequestBody ClienteDto clienteDto){
        return ResponseEntity.ok(clienteService.actualizarCliente(id,clienteDto));
    }
    @DeleteMapping("/eliminar/id")
    public ResponseEntity<String> eliminar(@PathVariable Long id){
        clienteService.eliminarCliente(id);
        return ResponseEntity.ok("cliente eliminado correctamente");
    }
}

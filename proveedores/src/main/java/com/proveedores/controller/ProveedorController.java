package com.proveedores.controller;

import com.proveedores.dto.ProveedorRequestDto;
import com.proveedores.dto.ProveedorResponseDto;
import com.proveedores.exepction.ProveedorException;
import com.proveedores.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @PostMapping("/crear")
    public ResponseEntity<?> crearProveedor(@RequestBody ProveedorRequestDto dto) {
        try {
            ProveedorResponseDto proveedorCreado = proveedorService.crearProveedor(dto);
            return ResponseEntity.ok(proveedorCreado);
        } catch (ProveedorException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // útil para ver el error en consola
            return ResponseEntity.status(500).body("Error interno del servidor: " + e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<List<ProveedorResponseDto>> listarProveedores() {
        List<ProveedorResponseDto> lista = proveedorService.listarProveedores();
        return ResponseEntity.ok(lista);
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<ProveedorResponseDto> obtenerProveedor(@PathVariable Long id) {
        ProveedorResponseDto proveedor = proveedorService.buscarPorId(id);
        return ResponseEntity.ok(proveedor);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ProveedorResponseDto> actualizarProveedor(@PathVariable Long id,
                                                                    @RequestBody ProveedorRequestDto dto) {
        ProveedorResponseDto actualizado = proveedorService.actualizarProveedor(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProveedor(@PathVariable Long id) {
        proveedorService.eliminarProveedor(id);
        return ResponseEntity.ok("Proveedor eliminado correctamente.");
    }

}

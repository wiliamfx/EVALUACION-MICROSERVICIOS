package com.producto.controller;

import com.producto.dto.ProductoDto;
import com.producto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Crear un producto (puede no tener proveedor al inicio)
    @PostMapping("/crear")
    public ResponseEntity<ProductoDto> crearProducto(@RequestBody ProductoDto productoDto) {
        ProductoDto creado = productoService.crearProducto(productoDto);
        return ResponseEntity.ok(creado);
    }
    @GetMapping("/listar")
    public ResponseEntity<List<ProductoDto>> obtenerTodos() {
        List<ProductoDto> lista = productoService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> obtenerProducto(@PathVariable Long id) {
        ProductoDto dto = productoService.obtenerProducto(id);
        return ResponseEntity.ok(dto);
    }

    // Asignar proveedor a un producto ya creado

    // Actualizar campos del producto (y opcionalmente el proveedor)
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ProductoDto> actualizarProducto(
            @PathVariable Long id,
            @RequestBody ProductoDto productoDto
    ) {
        ProductoDto actualizado = productoService.actualizarProducto(id, productoDto);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar un producto
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.ok("Producto eliminado correctamente");
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<ProductoDto> actualizarStock(@PathVariable Long id, @RequestParam int cantidad) {
        ProductoDto productoActualizado = productoService.actualizarStock(id, cantidad);
        return ResponseEntity.ok(productoActualizado);
    }


}

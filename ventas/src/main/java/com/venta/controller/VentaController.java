package com.venta.controller;

import com.venta.dto.ProductoDto;
import com.venta.dto.VentaDto;
import com.venta.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping
    public ResponseEntity<VentaDto> registrar(@RequestBody VentaDto ventaDto) {
        VentaDto response = ventaService.registrarVenta(ventaDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



}
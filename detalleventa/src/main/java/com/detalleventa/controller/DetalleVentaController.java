package com.detalleventa.controller;

import com.detalleventa.dto.DetalleVentaDto;
import com.detalleventa.service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/detalles")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService service;

    @PostMapping
    public ResponseEntity<DetalleVentaDto> registrar(@RequestBody DetalleVentaDto dto) {
        DetalleVentaDto guardado = service.registrar(dto);
        return new ResponseEntity<>(guardado, HttpStatus.CREATED);
    }
}

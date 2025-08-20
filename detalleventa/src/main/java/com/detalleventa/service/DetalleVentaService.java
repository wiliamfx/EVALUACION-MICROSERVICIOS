package com.detalleventa.service;

import com.detalleventa.dto.DetalleVentaDto;
import com.detalleventa.entity.DetalleVenta;
import com.detalleventa.mapper.DetalleVentaMapper;
import com.detalleventa.repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleVentaService {

    @Autowired
    private DetalleVentaRepository repository;

    public DetalleVentaDto registrar(DetalleVentaDto dto) {
        DetalleVenta entity = DetalleVentaMapper.toEntity(dto);
        DetalleVenta saved = repository.save(entity);
        return DetalleVentaMapper.toDto(saved);
    }
}

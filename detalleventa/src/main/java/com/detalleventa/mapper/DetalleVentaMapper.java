package com.detalleventa.mapper;

import com.detalleventa.dto.DetalleVentaDto;
import com.detalleventa.entity.DetalleVenta;
import org.springframework.stereotype.Component;

@Component
public class DetalleVentaMapper {

    // Convierte la entidad DetalleVenta en su correspondiente DTO
    public static DetalleVentaDto toDto(DetalleVenta entity) {
        return new DetalleVentaDto(
                entity.getDetalleid(),
                entity.getCantidad(),
                entity.getPrecioUnitario(),
                entity.getVentaid(),
                entity.getProductoid(),
                entity.getIgv(),
                entity.getSubtotal(),
                entity.getTotal()
        );
    }

    // Convierte el DTO de DetalleVenta en su correspondiente entidad
    public static DetalleVenta toEntity(DetalleVentaDto dto) {
        DetalleVenta detalleVenta = new DetalleVenta(
                dto.getDetalleid(),
                dto.getCantidad(),
                dto.getPrecioUnitario(),
                dto.getIgv(),
                dto.getSubtotal(),
                dto.getTotal(),
                dto.getVentaid(),
                dto.getProductoid()
        );
        return detalleVenta;
    }
}

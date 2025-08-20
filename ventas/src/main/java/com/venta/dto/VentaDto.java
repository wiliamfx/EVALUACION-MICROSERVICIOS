package com.venta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VentaDto {
    private Long ventaid;
    private LocalDateTime fecha;
    private Double igv;
    private Double subtotal;

    private Long clienteid;
    private Long usuarioid;
    private List<DetalleVentaDto> detallesVenta;
}

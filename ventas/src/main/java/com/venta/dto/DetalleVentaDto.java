package com.venta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVentaDto {
    private Long detalleid;
    private Long ventaid;
    private Long productoid;
    private Integer cantidad;
    private Double igv;

    private Double precioUnitario;
    private Double subtotal;

    private Double total;


}

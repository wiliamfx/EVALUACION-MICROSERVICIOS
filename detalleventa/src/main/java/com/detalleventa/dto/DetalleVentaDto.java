package com.detalleventa.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVentaDto {
    private Long detalleid;
    private int cantidad;
    private Double precioUnitario;
    private Long ventaid;
    private Long productoid;

    private Double igv;


    private Double subtotal;

    private Double total;
}
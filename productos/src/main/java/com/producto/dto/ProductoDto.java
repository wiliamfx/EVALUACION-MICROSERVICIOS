package com.producto.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDto {

    private Long productoid;
    private String descripcion;
    private String nombreproducto;
    private Double precio;
    private int stock;
    private ProveedorDto proveedorDto;
}

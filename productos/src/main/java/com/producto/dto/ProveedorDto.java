package com.producto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorDto {

    private Long proveedorid;
    private String email;
    private String nombre;
    private String ruc;
    private String telefono;
}

package com.venta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {
    private Long clienteid;
    private String nombre;
    private String apellidos;
    private String dni;
    private String email;
    private String telefono;
}

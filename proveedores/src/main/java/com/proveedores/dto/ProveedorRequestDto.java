package com.proveedores.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProveedorRequestDto {

    private String email;
    private String nombre;
    private String ruc;
    private String telefono;
}

package com.proveedores.mapper;

import com.proveedores.dto.ProveedorRequestDto;
import com.proveedores.dto.ProveedorResponseDto;
import com.proveedores.entity.Proveedor;
import org.springframework.stereotype.Component;
@Component
public class ProveedorMapper {

    /*public ProveedorResponseDto toDto(Proveedor proveedor) {
        return new ProveedorResponseDto(
                proveedor.getProveedorid(),
                proveedor.getEmail(),
                proveedor.getNombre(),
                proveedor.getRuc(),
                proveedor.getTelefono()
        );
    }

    public Proveedor toEntity(ProveedorRequestDto dto) {
        Proveedor proveedor = new Proveedor();
        proveedor.setEmail(dto.getEmail());
        proveedor.setNombre(dto.getNombre());
        proveedor.setRuc(dto.getRuc());
        proveedor.setTelefono(dto.getTelefono());
        return proveedor;
    }*/
}
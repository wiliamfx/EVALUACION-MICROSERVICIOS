package com.proveedores.mapper;


import com.proveedores.dto.ProveedorRequestDto;
import com.proveedores.dto.ProveedorResponseDto;
import com.proveedores.entity.Proveedor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProveedorMappe {
    Proveedor toEntity(ProveedorRequestDto dto);
    ProveedorResponseDto toDto(Proveedor entity);
}

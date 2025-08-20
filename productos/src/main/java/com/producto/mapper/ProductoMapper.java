package com.producto.mapper;

import com.producto.dto.ProductoDto;
import com.producto.dto.ProveedorDto;
import com.producto.entity.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoDto toDto(Producto producto, ProveedorDto proveedorDto) {
        return new ProductoDto(
                producto.getProductoid(),
                producto.getDescripcion(),
                producto.getNombreproducto(),
                producto.getPrecio(),
                producto.getStock(),
                proveedorDto
        );

    }

    public Producto toEntity(ProductoDto productoDto){
        Producto producto=new Producto();
        producto.setProductoid(productoDto.getProductoid());
        producto.setDescripcion(productoDto.getDescripcion());
        producto.setPrecio(productoDto.getPrecio());
        producto.setStock(productoDto.getStock());
        producto.setNombreproducto(productoDto.getNombreproducto());
        if(productoDto.getProveedorDto()!=null){
            producto.setProveedorid(productoDto.getProveedorDto().getProveedorid());
        }
        return producto;


    };

}
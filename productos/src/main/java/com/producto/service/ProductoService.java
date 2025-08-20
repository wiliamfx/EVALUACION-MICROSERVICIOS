package com.producto.service;

import com.producto.dto.ProductoDto;
import com.producto.dto.ProveedorDto;
import com.producto.entity.Producto;
import com.producto.exception.ProductoException;
import com.producto.mapper.ProductoMapper;
import com.producto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoMapper productoMapper;

    @Autowired
    private RestTemplate restTemplate;

    // URL base del microservicio de proveedores
    private final String PROVEEDOR_URL = "http://localhost:8092/api/proveedores/";

    // CREAR producto
    public ProductoDto crearProducto(ProductoDto productoDto) {

        if (productoRepository.existsByNombreproducto(productoDto.getNombreproducto())) {
            throw new ProductoException("El producto con nombre '" + productoDto.getNombreproducto() + "' ya existe");
        }
        // Validar si se envió proveedor
        if (productoDto.getProveedorDto() == null || productoDto.getProveedorDto().getProveedorid() == null) {
            throw new ProductoException("Debes proporcionar un proveedor válido para registrar el producto");
        }

        // Obtener y validar el proveedor llamando al microservicio
        ProveedorDto proveedorDto = obtenerProveedorSeguro(productoDto.getProveedorDto().getProveedorid());
        if (proveedorDto == null) {
            throw new ProductoException("Proveedor no encontrado con ID: " + productoDto.getProveedorDto().getProveedorid());
        }

        // Mapear el producto DTO a entidad
        Producto producto = productoMapper.toEntity(productoDto);
        producto.setProveedorid(proveedorDto.getProveedorid());

        // Guardar en la base de datos
        Producto guardado = productoRepository.save(producto);

        // Retornar DTO completo
        return productoMapper.toDto(guardado, proveedorDto);
    }

    // ACTUALIZAR STOCK producto
    public ProductoDto actualizarStock(Long id, int cantidad) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoException("Producto no encontrado con ID: " + id));

        // Actualizar el stock del producto
        int nuevoStock = producto.getStock() - cantidad;
        if (nuevoStock < 0) {
            throw new ProductoException("No hay suficiente stock para realizar esta operación");
        }

        producto.setStock(nuevoStock);

        // Guardar el producto con el nuevo stock
        Producto actualizado = productoRepository.save(producto);

        // Obtener el proveedor actualizado
        ProveedorDto proveedorDto = obtenerProveedorSeguro(producto.getProveedorid());

        return productoMapper.toDto(actualizado, proveedorDto);
    }

    public List<ProductoDto> listarTodos() {
        List<Producto> productos = productoRepository.findAll();

        return productos.stream().map(producto -> {
            ProveedorDto proveedorDto = obtenerProveedorSeguro(producto.getProveedorid());
            return productoMapper.toDto(producto, proveedorDto);
        }).collect(Collectors.toList());
    }

    // OBTENER producto
    public ProductoDto obtenerProducto(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoException("Producto no encontrado con ID: " + id));

        ProveedorDto proveedorDto = obtenerProveedorSeguro(producto.getProveedorid());
        return productoMapper.toDto(producto, proveedorDto);
    }

    // ACTUALIZAR producto (sin cambiar proveedor)
    public ProductoDto actualizarProducto(Long id, ProductoDto productoDto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoException("Producto no encontrado con ID: " + id));

        // Actualizar los campos
        producto.setDescripcion(productoDto.getDescripcion());
        producto.setNombreproducto(productoDto.getNombreproducto());
        producto.setPrecio(productoDto.getPrecio());
        producto.setStock(productoDto.getStock());

        // Validar proveedor y actualizar si es necesario
        ProveedorDto proveedorDto = null;
        if (productoDto.getProveedorDto() != null && productoDto.getProveedorDto().getProveedorid() != null) {
            proveedorDto = obtenerProveedorSeguro(productoDto.getProveedorDto().getProveedorid());
            if (proveedorDto == null) {
                throw new ProductoException("Proveedor no encontrado con ID: " + productoDto.getProveedorDto().getProveedorid());
            }
            producto.setProveedorid(productoDto.getProveedorDto().getProveedorid());
        }

        Producto actualizado = productoRepository.save(producto);
        return productoMapper.toDto(actualizado, proveedorDto);
    }


    // ELIMINAR producto
    public void eliminarProducto(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoException("No se puede eliminar. Producto no encontrado con ID: " + id));
        productoRepository.delete(producto);
    }


    private ProveedorDto obtenerProveedorSeguro(Long proveedorId) {
        if (proveedorId == null) return null;
        try {
            return restTemplate.getForObject(PROVEEDOR_URL + proveedorId, ProveedorDto.class);
        } catch (RestClientException e) {
            return null;
        }
    }
}

package com.venta.service;

import com.venta.dto.*;
import com.venta.entity.Venta;
import com.venta.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String CLIENTE_URL = "http://localhost:8094/api/clientes/";
    private final String USUARIO_URL = "http://localhost:8090/api/usuarios/";
    private final String DETALLE_URL = "http://localhost:8095/api/detalles";
    private final String PRODUCTO_URL = "http://localhost:8093/api/productos/";

    // Validación de existencia de cliente en el microservicio
    private void validarClienteExistente(Long clienteid) {
        try {
            restTemplate.getForObject(CLIENTE_URL + clienteid, ClienteDto.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("No se encontró el cliente con ID " + clienteid);
        }
    }

    // Validación de existencia de usuario en el microservicio
    private void validarUsuarioExistente(Long usuarioid) {
        try {
            restTemplate.getForObject(USUARIO_URL + usuarioid, UsuarioDto.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("No se encontró el usuario con ID " + usuarioid);
        }
    }

    // Validación de existencia de producto en el microservicio
    private ProductoDto validarProductoExistente(Long productoid) {
        try {
            ProductoDto producto = restTemplate.getForObject(PRODUCTO_URL + productoid, ProductoDto.class);
            if (producto == null) {
                throw new RuntimeException("No se encontró el producto con ID " + productoid);
            }
            return producto;
        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("No se encontró el producto con ID " + productoid);
        }
    }

    public VentaDto registrarVenta(VentaDto ventaDto) {
        // Validar que el cliente exista
        validarClienteExistente(ventaDto.getClienteid());

        // Validar que el usuario exista
        validarUsuarioExistente(ventaDto.getUsuarioid());

        // Calcular el subtotal, IGV y total
        double subtotal = 0;
        for (DetalleVentaDto detalle : ventaDto.getDetallesVenta()) {
            // Validar que el producto exista y obtener el precio unitario
            ProductoDto producto = validarProductoExistente(detalle.getProductoid());

            // Verificar el stock disponible del producto
            if (producto.getStock() < detalle.getCantidad()) {
                throw new RuntimeException("No tenemos suficiente stock para el producto con ID " + detalle.getProductoid() + ". Stock disponible: " + producto.getStock());
            }

            // Obtener el precio unitario desde el microservicio
            double precioUnitario = producto.getPrecio();
            detalle.setPrecioUnitario(precioUnitario);

            // Calcular el subtotal, IGV y total para este detalle
            double detalleSubtotal = detalle.getCantidad() * precioUnitario;
            double detalleIgv = detalleSubtotal * 0.18;  // 18% de IGV
            double detalleTotal = detalleSubtotal + detalleIgv;

            // Asignar los valores calculados en el detalle
            detalle.setSubtotal(detalleSubtotal);
            detalle.setIgv(detalleIgv);
            detalle.setTotal(detalleTotal);

            // Sumar al subtotal general
            subtotal += detalleSubtotal;
        }

        double igv = subtotal * 0.18;  // 18% de IGV para la venta completa
        double total = subtotal + igv;

        // Recuperar cliente y usuario desde sus respectivos microservicios
        ClienteDto cliente = restTemplate.getForObject(CLIENTE_URL + ventaDto.getClienteid(), ClienteDto.class);
        UsuarioDto usuario = restTemplate.getForObject(USUARIO_URL + ventaDto.getUsuarioid(), UsuarioDto.class);

        // Crear la venta en la base de datos
        Venta venta = new Venta();
        venta.setFecha(LocalDateTime.now());
        venta.setSubtotal(subtotal);
        venta.setIgv(igv);
        venta.setTotal(total);
        venta.setClienteid(cliente.getClienteid());
        venta.setUsuarioid(usuario.getUsuarioid());

        venta = ventaRepository.save(venta);  // Guardamos la venta

        // Procesar los detalles de la venta
        for (DetalleVentaDto detalle : ventaDto.getDetallesVenta()) {
            // Enviar el detalle de la venta al microservicio DetalleVenta
            detalle.setVentaid(venta.getVentaid());
            restTemplate.postForObject(DETALLE_URL, detalle, Void.class);

            // Actualizar el stock en el microservicio Producto
            ProductoDto producto = restTemplate.getForObject(PRODUCTO_URL + detalle.getProductoid(), ProductoDto.class);
            if (producto != null && producto.getStock() >= detalle.getCantidad()) {
                // Actualizar el stock en el microservicio Producto
                restTemplate.put(PRODUCTO_URL + detalle.getProductoid() + "/stock?cantidad=" + detalle.getCantidad(), null);
            } else {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombreproducto());
            }
        }

        // Actualizar el DTO de la venta con el ID y fecha generados
        ventaDto.setVentaid(venta.getVentaid());
        ventaDto.setFecha(venta.getFecha());
        ventaDto.setSubtotal(subtotal);
        ventaDto.setIgv(igv);

        return ventaDto;  // Retornar el DTO actualizado
    }
}

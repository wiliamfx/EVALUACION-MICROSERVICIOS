package com.venta.entity;

import com.venta.dto.DetalleVentaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "ventas")
@AllArgsConstructor
@NoArgsConstructor

public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ventaid")
    private Long ventaid;
    @Column(name = "fecha")
    private LocalDateTime fecha;
    @Column(name = "igv")
    private Double igv;
    @Column(name = "subtotal")
    private Double subtotal;
    @Column(name = "clienteid",nullable = false)
    private Long clienteid;

    private Double total;

    @Column(name = "usuarioid",nullable = false)
    private Long usuarioid;




}

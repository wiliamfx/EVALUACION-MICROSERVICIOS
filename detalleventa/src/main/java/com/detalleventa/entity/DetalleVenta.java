package com.detalleventa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalleventa")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detalleid")
    private Long detalleid;


    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "preciounitario")
    private Double precioUnitario;

    @Column(name = "igv")
    private Double igv;

    @Column(name = "subtotal")
    private Double subtotal;
    @Column(name = "total")
    private Double total;



    @Column(name = "ventaid")
    private Long ventaid;
    @Column(name = "productoid")
    private Long productoid;

}

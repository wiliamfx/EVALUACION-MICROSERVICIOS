package com.producto.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.lang.model.element.Name;

@Entity
@Data
@Table(name = "productos")
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productoid")
    private Long productoid;

    @Column(name = "descripcion",nullable = false)
    private String descripcion;

    @Column(name = "nombreproducto",nullable = false,unique = true)
    private String nombreproducto;

    @Column(name = "precio",nullable = false)
    private Double precio;

    @Column(name = "stock",nullable = false)
    private int stock;

    @Column(name = "proveedorid")
    private Long proveedorid;


}

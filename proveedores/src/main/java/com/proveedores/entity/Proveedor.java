package com.proveedores.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proveedores")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Proveedor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proveedorid")
    private Long proveedorid;

    @Column(name = "email",nullable = false,length = 50,unique = true)
    private String email;

    @Column(name = "nombre",nullable = false,unique = true)
    private String nombre;

    @Column(name = "ruc",nullable = false,unique = true,length = 11)
    private String ruc;

    @Column(name = "telefono",nullable = false,unique = true,length = 9)
    private String telefono;
}

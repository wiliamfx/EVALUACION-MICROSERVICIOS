package com.clientes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clienteid")
    private Long clienteid;

    @Column(name = "nombre",nullable = false)
    private String nombre;

    @Column(name = "apellidos",nullable = false)
    private String apellidos;

    @Column(name = "dni",nullable = false,unique = true)
    private String dni;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "telefono",nullable = false)
    private String telefono;


}

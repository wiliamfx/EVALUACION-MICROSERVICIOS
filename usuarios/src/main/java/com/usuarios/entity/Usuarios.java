package com.usuarios.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuarioid")
    private Long usuarioid;

    @Column(name = "username",unique = true,nullable = false)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "nombre",nullable = false,length = 30)
    private String nombre;

    @Column(name = "apellidos",nullable = false,length = 40)
    private String apellidos;

    @Column(name = "dni",nullable = false,unique = true,length = 8)
    private String dni;
    
    @Column(name = "rol_id")
    private Long rolid;

    



}

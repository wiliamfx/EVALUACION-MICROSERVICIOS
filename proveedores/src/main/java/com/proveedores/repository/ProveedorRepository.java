package com.proveedores.repository;

import com.proveedores.entity.Proveedor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor,Long> {
    boolean existsByEmail(String email);
    boolean existsByRuc(String ruc);
    boolean existsByTelefono(String telefono);
}

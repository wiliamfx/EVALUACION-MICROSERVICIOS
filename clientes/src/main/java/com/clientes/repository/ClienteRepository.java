package com.clientes.repository;

import com.clientes.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    boolean existsById(Long clienteid);
    boolean existsBydni(String dni);
    boolean existsByEmail(String email);

}

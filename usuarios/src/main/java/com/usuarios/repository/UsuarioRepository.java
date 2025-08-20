package com.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.usuarios.entity.Usuarios;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {
    boolean existsByDni(String dni);
    boolean existsByUsername(String username);

    Optional<Usuarios> findByUsername(String username);
}

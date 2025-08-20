package com.roles.roles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.roles.roles.entity.Rol;

import java.util.Optional;

@Repository
public interface Rolrepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByRolnombre(String rolnombre);
    // Aquí puedes agregar métodos personalizados si es necesario

}

package com.proyecto.Zephyra.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.Zephyra.model.Carrito;
import com.proyecto.Zephyra.model.User;

public interface CarritoRepository extends JpaRepository<Carrito, Integer> {

    Optional<Carrito> findByUsuario(User usuario);
}
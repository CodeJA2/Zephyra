package com.proyecto.Zephyra.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.Zephyra.entidades.Producto;


public interface ProductoRepository extends JpaRepository<Producto, Long> {
}


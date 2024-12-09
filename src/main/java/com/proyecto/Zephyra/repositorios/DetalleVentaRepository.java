package com.proyecto.Zephyra.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.Zephyra.model.DetalleVenta;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {
    
}

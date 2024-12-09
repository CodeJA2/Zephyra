package com.proyecto.Zephyra.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.Zephyra.model.CarritoItem;

public interface CarritoItemRepository extends JpaRepository<CarritoItem, Integer> {
    
    List<CarritoItem> findByCarritoId(Integer carritoId);
}

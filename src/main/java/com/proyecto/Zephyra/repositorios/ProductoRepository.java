package com.proyecto.Zephyra.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.Zephyra.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Obtener Productos de la misma Categoria en la Base de Datos
    List<Producto> findByDestacadoTrue();
    List<Producto> findByCategoriaId(Long categoriaId);
}


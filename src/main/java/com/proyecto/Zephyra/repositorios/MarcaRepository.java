package com.proyecto.Zephyra.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.Zephyra.entidades.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
}
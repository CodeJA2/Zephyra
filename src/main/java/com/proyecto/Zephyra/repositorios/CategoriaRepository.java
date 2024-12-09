package com.proyecto.Zephyra.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.Zephyra.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
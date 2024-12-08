package com.proyecto.Zephyra.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.Zephyra.model.Categoria;
import com.proyecto.Zephyra.repositorios.CategoriaRepository;
import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // C - Create (Crear):
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // R - Read (Leer):
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    // U - Update (Actualizar):
    public Categoria actualizarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // D - Delete (Eliminar):
    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    // Obtener una Categoria segun su ID
    public Categoria obtenerCategoriaPorId(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    // conteo de todas las categorias registradas:
    public long contarCategorias() {
        return categoriaRepository.count();
    }
}

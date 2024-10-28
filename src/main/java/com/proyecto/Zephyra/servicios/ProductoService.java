package com.proyecto.Zephyra.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.Zephyra.entidades.Categoria;
import com.proyecto.Zephyra.entidades.Producto;
import com.proyecto.Zephyra.repositorios.CategoriaRepository;
import com.proyecto.Zephyra.repositorios.ProductoRepository;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // C - Create (Crear):
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // R - Read (Leer):
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    // U - Update (Actualizar):
    public Producto actualizarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // D - Delete (Eliminar):
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    // metodo para obtener un producto de la base de datos
    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public void guardarProductoConCategorias(Producto producto, List<Long> categoriaIds) {
        // Busca las categorías por sus IDs
        List<Categoria> categorias = categoriaRepository.findAllById(categoriaIds);
        
        // Asigna las categorías al producto
        producto.setCategorias(categorias);

        // Guarda el producto
        productoRepository.save(producto);
    }

    // Método para actualizar un producto con sus categorías
    public void actualizarProductoConCategorias(Long id, Producto productoActualizado, List<Long> categoriaIds) {
        // Busca el producto existente por su ID
        Producto productoExistente = productoRepository.findById(id).orElse(null);
        if (productoExistente != null) {
            // Actualiza los atributos del producto
            productoExistente.setNombre(productoActualizado.getNombre());
            productoExistente.setPrecio(productoActualizado.getPrecio());
            productoExistente.setDescripcion(productoActualizado.getDescripcion());
            productoExistente.setStock(productoActualizado.getStock());

            // Busca las categorías por sus IDs
            List<Categoria> categorias = categoriaRepository.findAllById(categoriaIds);
            // Asigna las categorías al producto
            productoExistente.setCategorias(categorias);

            // Guarda el producto actualizado en la base de datos
            productoRepository.save(productoExistente);
        }
    }




    // R - Read (Leer):
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }
}

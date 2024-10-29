package com.proyecto.Zephyra.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.Zephyra.entidades.Categoria;
import com.proyecto.Zephyra.entidades.Marca;
import com.proyecto.Zephyra.entidades.Producto;
import com.proyecto.Zephyra.repositorios.CategoriaRepository;
import com.proyecto.Zephyra.repositorios.MarcaRepository;
import com.proyecto.Zephyra.repositorios.ProductoRepository;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private MarcaRepository marcaRepository;

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

    // Obtener un Producto segun su ID
    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    // Guardar un Producto, su Marca y sus Categorías
    public void guardarProductoConCategoriasYMarca(Producto producto, List<Long> categoriaIds, Long marcaId) {
        // Busca las categorías por sus IDs
        List<Categoria> categorias = categoriaRepository.findAllById(categoriaIds);
        // Asigna las categorías al producto
        producto.setCategorias(categorias);
        // Busca la marca por su ID
        Marca marca = marcaRepository.findById(marcaId).orElse(null);
        // Asigna la marca al producto
        producto.setMarca(marca);
        // Guarda el producto
        productoRepository.save(producto);
    }

   // Actualizar un Producto, su Marca y sus Categorías
    public void actualizarProductoConCategoriasYMarca(Long id, Producto productoActualizado, List<Long> categoriaIds, Long marcaId) {
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
            // Busca la marca por su ID
            Marca marca = marcaRepository.findById(marcaId).orElse(null);
            productoExistente.setMarca(marca);
            // Guarda el producto actualizado en la base de datos
            productoRepository.save(productoExistente);
        }
    }

    // R - Read (Leer) categorías:
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    // Productos de una misma Categoria
    public List<Producto> listarProductosPorCategoria(Long categoriaId) {
        // Lógica para obtener productos de la categoría específica
        return productoRepository.findByCategoriasId(categoriaId);
    }

    // R - Read (Leer) marcas:
    public List<Marca> listarMarcas() {
        return marcaRepository.findAll();
    }
}

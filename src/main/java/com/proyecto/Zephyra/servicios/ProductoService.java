package com.proyecto.Zephyra.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.Zephyra.model.Categoria;
import com.proyecto.Zephyra.model.Producto;
import com.proyecto.Zephyra.model.Talla;
import com.proyecto.Zephyra.repositorios.CategoriaRepository;

import com.proyecto.Zephyra.repositorios.ProductoRepository;
import java.util.List;
import java.util.Optional;

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
    public void eliminarProducto(Integer id) {
        productoRepository.deleteById(id);
    }

    // Obtener un Producto segun su ID
    public Producto obtenerProductoPorId(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }



    public void actualizarProductoConCategoria(Integer id, Producto productoActualizado) {
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El producto con el ID especificado no existe."));
        
        // Mapear las propiedades desde el producto actualizado al existente
        actualizarCamposProducto(productoExistente, productoActualizado);
    
        productoRepository.save(productoExistente);
    }
    
    private void actualizarCamposProducto(Producto productoExistente, Producto productoActualizado) {
        productoExistente.setNombre(productoActualizado.getNombre());
        productoExistente.setPrecio(productoActualizado.getPrecio());
        productoExistente.setColor(productoActualizado.getColor());
        productoExistente.setImageUrl(productoActualizado.getImageUrl());
        productoExistente.setDescripcion(productoActualizado.getDescripcion());
        productoExistente.setEnOferta(productoActualizado.getEnOferta());
        productoExistente.setPrecioOferta(productoActualizado.getPrecioOferta());
        productoExistente.setDestacado(productoActualizado.getDestacado());
        productoExistente.setCategoria(productoActualizado.getCategoria());
    }

    // R - Read (Leer) categorías:
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    // Productos de una misma Categoria
    public List<Producto> listarProductosPorCategoria(Integer categoriaId) {
        // Lógica para obtener productos de la categoría específica
        return productoRepository.findByCategoriaId(categoriaId);
    }


    // conteo de todas los productos registradas:
    public long contarProductos() {
        return productoRepository.count();
    }

    public List<Producto> obtenerProductosDestacados() {
        return productoRepository.findByDestacadoTrue();
    }

     public void disminuirStock(Integer productoId, String talla, int cantidad) {
        // Buscar el producto por su ID y talla
        Optional<Producto> productoOpt = productoRepository.findById(productoId);
        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            // Buscar y actualizar la cantidad de stock de la talla específica
            for (Talla tallaItem : producto.getTallas()) {
                if (tallaItem.getTamaño().equals(talla)) {
                    int stockActual = tallaItem.getStock();
                    if (stockActual >= cantidad) {
                        tallaItem.setStock(stockActual - cantidad);
                        productoRepository.save(producto);
                    } else {
                        throw new RuntimeException("Stock insuficiente para la talla " + talla);
                    }
                    break;
                }
            }
        } else {
            throw new RuntimeException("Producto no encontrado con ID: " + productoId);
        }
    }
}

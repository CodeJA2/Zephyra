package com.proyecto.Zephyra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.proyecto.Zephyra.entidades.Categoria;
import com.proyecto.Zephyra.entidades.Producto;
import com.proyecto.Zephyra.servicios.ProductoService;
import java.util.List;

@Controller
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    // C - Create (Crear):
    @GetMapping("/ADM/productos/nuevo")
    public String crearProducto(Model model) {
        model.addAttribute("producto", new Producto());
        List<Categoria> listaCategorias = productoService.listarCategorias();
        model.addAttribute("listaCategorias", listaCategorias); // Añadir todas las categorías
        return "ADM_crearProducto";
    }

    // R - Read (Leer):
    @GetMapping("/ADM/productos")
    public String allProductos(Model model) {
        List<Producto> lista = productoService.listarProductos();
        model.addAttribute("productos", lista);
        return "ADM_listarProductos";
    }

    @PostMapping("/ADM/productos")
    public String guardarProducto(@ModelAttribute("producto") Producto producto,
            @RequestParam("categoriaIds") List<Long> categoriaIds) {
        productoService.guardarProductoConCategorias(producto, categoriaIds);
        return "redirect:/ADM/productos";
    }

    // U - Update (Actualizar):
    @GetMapping("/ADM/productos/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        Producto producto = productoService.obtenerProductoPorId(id);
        if (producto != null) {
            model.addAttribute("producto", producto);
            List<Categoria> listaCategorias = productoService.listarCategorias();
            model.addAttribute("categorias", listaCategorias); // Añadir todas las categorías
            return "ADM_editarProducto";
        } else {
            return "redirect:/ADM/productos"; // Redirige si no se encuentra el producto
        }
    }

    @PostMapping("/ADM/productos/actualizar/{id}")
    public String actualizarProducto(@PathVariable("id") Long id,
            @ModelAttribute("producto") Producto productoActualizado,
            @RequestParam("categoriaIds") List<Long> categoriaIds) {
        productoService.actualizarProductoConCategorias(id, productoActualizado, categoriaIds);
        return "redirect:/ADM/productos";
    }

    // D - Delete (Eliminar):
    @GetMapping("/ADM/productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/ADM/productos";
    }

    // Mostrar Productos de una misma Categoria
    @GetMapping("/ADM/productos/categoria/{id}")
    public String listarProductosPorCategoria(@PathVariable Long id, Model model) {
        List<Producto> productosPorCategoria = productoService.listarProductosPorCategoria(id);
        model.addAttribute("productos", productosPorCategoria);
        return "ADM_listarProductosPorCategoria"; // Devuelve la vista correspondiente
    }

    // Mostrar Productos de una misma Categoria
    @GetMapping("/productos/categoria/{id}")
    public String productosPorCategoria(@PathVariable Long id, Model model) {
        List<Producto> productosPorCategoria = productoService.listarProductosPorCategoria(id);
        model.addAttribute("productos", productosPorCategoria);
        return "galeria"; // Devuelve la vista correspondiente
    }

}

package com.proyecto.Zephyra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.proyecto.Zephyra.model.Categoria;
import com.proyecto.Zephyra.model.Producto;
import com.proyecto.Zephyra.servicios.CategoriaService;
import com.proyecto.Zephyra.servicios.ProductoService;
import java.util.List;

@Controller
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    // C - CREATE (Crear producto):
    @GetMapping("/ADM/productos/nuevo")
    public String crearProducto(Model model) {
        model.addAttribute("producto", new Producto());
        List<Categoria> listaCategorias = productoService.listarCategorias();
        model.addAttribute("listaCategorias", listaCategorias); // Añadir todas las categorías
        return "ADM_crearProducto";
    }

    @PostMapping("/ADM/productos")
    public String guardarProducto(@ModelAttribute("producto") Producto producto) { 
        productoService.guardarProducto(producto);
        return "redirect:/ADM/productos";
    }

    // R - READ (Leer producto):
    @GetMapping("/ADM/productos")
    public String listarProductos(Model model) {
        List<Producto> lista = productoService.listarProductos();
        model.addAttribute("productos", lista);
        return "ADM_listarProductos";
    }

    // U - UPDATE (Actualizar producto):
    @GetMapping("/ADM/productos/editar/{id}")
    public String editarProducto(@PathVariable("id") Integer id, Model model) {
        Producto producto = productoService.obtenerProductoPorId(id);
        if (producto != null) {
            model.addAttribute("producto", producto);

            List<Categoria> listaCategorias = productoService.listarCategorias();
            model.addAttribute("listaCategorias", listaCategorias);


            return "ADM_editarProducto";
        } else {
            return "redirect:/ADM/productos";
        }
    }

    @PostMapping("/ADM/productos/actualizar/{id}")
    public String actualizarProducto(@PathVariable("id") Integer id,
            @ModelAttribute("producto") Producto productoActualizado) {
        productoService.actualizarProductoConCategoria(id, productoActualizado);
        return "redirect:/ADM/productos";
    }

    // D - DELETE (Eliminar producto):
    @GetMapping("/ADM/productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") Integer id) {
        productoService.eliminarProducto(id);
        return "redirect:/ADM/productos";
    }

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        // Obtener todas las categorías
        List<Categoria> listaCategorias = categoriaService.listarCategorias();
        model.addAttribute("listaCategorias", listaCategorias);

        // Pasar un objeto Producto vacío para el formulario
        model.addAttribute("producto", new Producto());
        return "formularioProducto"; // Nombre de la plantilla HTML
    }

}

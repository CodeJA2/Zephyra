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
// @RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    // @Autowired
    // private CategoriaService categoriaService;

    // C - Create (Crear):
    @GetMapping("/productos/nuevo")
    public String crearProducto(Model model) {
        model.addAttribute("producto", new Producto());
        List<Categoria> listaCategorias = productoService.listarCategorias();
        model.addAttribute("listaCategorias", listaCategorias); // Añadir todas las categorías
        return "ADM_crearProducto";
    }

    // R - Read (Leer):
    @GetMapping("/productos")
    public String listarProductos(Model model) {
        List<Producto> lista = productoService.listarProductos();
        model.addAttribute("productos", lista);
        return "ADM_listarProductos";
    }

    // @PostMapping("/productos")
    // public String guardarProducto(@ModelAttribute("producto") Producto producto) {
    //     productoService.guardarProducto(producto);
    //     return "redirect:/productos";
    // }

    @PostMapping("/productos")
    public String guardarProducto(@ModelAttribute("producto") Producto producto, @RequestParam("categoriaIds") List<Long> categoriaIds) {
        productoService.guardarProductoConCategorias(producto, categoriaIds);
        return "redirect:/productos";
    }

    // U - Update (Actualizar):
    @GetMapping("/productos/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        Producto producto = productoService.obtenerProductoPorId(id);
        if (producto != null) {
            model.addAttribute("producto", producto);
            List<Categoria> listaCategorias = productoService.listarCategorias();
            model.addAttribute("categorias", listaCategorias); // Añadir todas las categorías
            return "ADM_editarProducto";
        } else {
            return "redirect:/productos"; // Redirige si no se encuentra el producto
        }
    }

    @PostMapping("/productos/actualizar/{id}")
    public String actualizarProducto(@PathVariable("id") Long id,
                                    @ModelAttribute("producto") Producto productoActualizado,
                                    @RequestParam("categoriaIds") List<Long> categoriaIds) {
        productoService.actualizarProductoConCategorias(id, productoActualizado, categoriaIds);
        return "redirect:/productos";
    }

    // @PostMapping("/productos/actualizar/{id}")
    // public String actualizarProducto(@PathVariable("id") Long id,
    //         @ModelAttribute("producto") Producto productoActualizado) {
    //     Producto productoExistente = productoService.obtenerProductoPorId(id);
    //     if (productoExistente != null) {
    //         productoExistente.setNombre(productoActualizado.getNombre());
    //         productoExistente.setPrecio(productoActualizado.getPrecio());
    //         productoExistente.setDescripcion(productoActualizado.getDescripcion());
    //         productoService.actualizarProducto(productoExistente);
    //     }
    //     return "redirect:/productos";
    // }

    // D - Delete (Eliminar):
    @GetMapping("/productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/productos";
    }
    
}

package com.proyecto.Zephyra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.proyecto.Zephyra.entidades.Categoria;
import com.proyecto.Zephyra.entidades.Marca;
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
        List<Marca> marcas = productoService.listarMarcas();
        model.addAttribute("listaMarcas", marcas);  // Añadir todas las marcas
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
    public String guardarProducto(@ModelAttribute("producto") Producto producto,  // Capturar el producto desde el formulario
                                  @RequestParam("categoriaIds") List<Long> categoriaIds,  // Capturar las categorias desde el formulario
                                  @RequestParam("marcaId") Long marcaId) { // Capturar marcaId desde el formulario
        productoService.guardarProductoConCategoriasYMarca(producto, categoriaIds, marcaId);
        return "redirect:/ADM/productos";
    }

    // U - Update (Actualizar):
    @GetMapping("/ADM/productos/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        Producto producto = productoService.obtenerProductoPorId(id);
        if (producto != null) {
            model.addAttribute("producto", producto);

            List<Categoria> listaCategorias = productoService.listarCategorias();
            model.addAttribute("listaCategorias", listaCategorias); // Añadir todas las categorías
            
            List<Marca> marcas = productoService.listarMarcas();
            model.addAttribute("listaMarcas", marcas); // Añadir todas las marcas
            
            return "ADM_editarProducto";
        } else {
            return "redirect:/ADM/productos";
        }
    }

    @PostMapping("/ADM/productos/actualizar/{id}")
    public String actualizarProducto(@PathVariable("id") Long id,
                                     @ModelAttribute("producto") Producto productoActualizado,
                                     @RequestParam("categoriaIds") List<Long> categoriaIds,
                                     @RequestParam("marcaId") Long marcaId) { // Capturar marcaId
        productoService.actualizarProductoConCategoriasYMarca(id, productoActualizado, categoriaIds, marcaId); // Manejar marca también
        return "redirect:/ADM/productos";
    }

    // D - Delete (Eliminar):
    @GetMapping("/ADM/productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/ADM/productos";
    }

    // Mostrar Productos de una misma Categoria (Administrador)
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

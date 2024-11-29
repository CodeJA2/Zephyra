package com.proyecto.Zephyra.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.proyecto.Zephyra.entidades.Marca;
import com.proyecto.Zephyra.entidades.Producto;
import com.proyecto.Zephyra.servicios.CategoriaService;
import com.proyecto.Zephyra.servicios.MarcaService;
import com.proyecto.Zephyra.servicios.ProductoService;


@Controller
public class ADM_Controller {
    
    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private MarcaService marcaService;

    // Pagina pricipal de administrador
    @GetMapping("/ADM")
    public String mostrarAdministrador(Model model) {
        long totalProductos = productoService.contarProductos();
        long totalCategorias = categoriaService.contarCategorias();
        long totalMarcas = marcaService.contarMarcas();
        model.addAttribute("totalProductos", totalProductos);
        model.addAttribute("totalCategorias", totalCategorias);
        model.addAttribute("totalMarcas", totalMarcas);
        return "ADM_index";
    }

    // Mostrar Productos de una misma Categoria
    @GetMapping("/ADM/productos/categoria/{id}")
    public String listarProductosPorCategoria(@PathVariable Long id, Model model) {
        List<Producto> productosPorCategoria = productoService.listarProductosPorCategoria(id);
        model.addAttribute("productos", productosPorCategoria);
        return "ADM_listarProductosPorCategoria";
    }

    // devuelve una respuesta HTTP que contiene tanto la cabeza (headers) como el cuerpo (body) de la respuesta.
    @GetMapping("/getListMarcasJSON")
    public ResponseEntity<List<Marca>> listarColaboradorJSON() {
        List<Marca> lista = marcaService.listarMarcas();
        return ResponseEntity.ok(lista);
    }

    
}

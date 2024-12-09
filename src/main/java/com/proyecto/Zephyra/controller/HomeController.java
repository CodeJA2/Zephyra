package com.proyecto.Zephyra.controller;


import com.proyecto.Zephyra.model.Categoria;
import com.proyecto.Zephyra.model.Contactanos;
import com.proyecto.Zephyra.model.Devolucion;
import com.proyecto.Zephyra.model.LibroReclamaciones;
import com.proyecto.Zephyra.model.Producto;
import com.proyecto.Zephyra.model.Sugerencia;
import com.proyecto.Zephyra.model.User;

import com.proyecto.Zephyra.servicios.CategoriaService;
import com.proyecto.Zephyra.servicios.ProductoService;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class HomeController {
    
    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;


   @ModelAttribute("categoriasHombre")
    public List<Categoria> categoriasHombre() {
        return categoriaService.listarCategorias().stream()
                .filter(c -> "Hombre".equalsIgnoreCase(c.getPara()))
                .collect(Collectors.toList());
    }

    @ModelAttribute("categoriasMujer")
    public List<Categoria> categoriasMujer() {
        return categoriaService.listarCategorias().stream()
                .filter(c -> "Mujer".equalsIgnoreCase(c.getPara()))
                .collect(Collectors.toList());
    }


    @GetMapping({"/"})
    public String listarProductos(Model model) {
        List<Producto> lista = productoService.listarProductos();
        List<Producto> productosDestacados = productoService.obtenerProductosDestacados();
      
        model.addAttribute("productos", productosDestacados);
        model.addAttribute("productos", lista);

        return "index";
    }

    @ModelAttribute("usuario")
    public User obtenerUsuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal(); // Retorna el usuario autenticado
        }
        return null; // Retorna null si no hay usuario autenticado
    }

    @GetMapping("/public/detalleProducto/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Integer id, Model model) {
        Producto producto = productoService.obtenerProductoPorId(id);
        if (producto != null) {
            model.addAttribute("producto", producto);
            return "detalleProducto";
        } else {
            return "redirect:/"; // Redirige si no se encuentra el colaborador
        }
    }

    @GetMapping("/public/editarCarrito")
    public String editarCarrito() {
        return "editarCarrito";
    }

    @GetMapping("/public/paginaPago")
    public String paginaPago() {
        return "paginaPago";
    }

    @GetMapping("/public/politicaEnvios")
    public String politicaEnvios() {
        return "politicaEnvios";
    }

    @GetMapping("/public/politicaDevoluciones")
    public String politicaDevoluciones() {
        return "politicaDevoluciones";
    }

    @GetMapping("/public/preguntasFrecuentes")
    public String preguntasFrecuentes() {
        return "preguntasFrecuentes";
    }

    @GetMapping("/public/servicioEntrega")
    public String servicioEntrega() {
        return "servicioEntrega";
    }

    // Formulario de Sugerencia
    @GetMapping("/public/sugerencia")
    public String mostrarSugerencia(Model model) {
        model.addAttribute("sugerencia", new Sugerencia());
        return "formularioSugerencia";
    }

    @PostMapping("/public/sugerencia")
    public String enviarSugerencia(@ModelAttribute Sugerencia sugerencia, Model model) {
        // Aquí se puede procesar la sugerencia o guardarla en la base de datos
        model.addAttribute("sugerencia", sugerencia);
        return "envioSugerencia";
    }

    // Formulario de devolucion
    @GetMapping("/public/formularioDevolucion")
    public String mostrarDevolucion(Model model) {
        model.addAttribute("devolucion", new Devolucion());
        return "formularioDevolucion";
    }

    @PostMapping("/public/envioDevolucion")
    public String enviarDevolucion(@ModelAttribute Devolucion devolucion, Model model) {
        model.addAttribute("devolucion", devolucion);
        return "envioDevolucion";
    }

    // Formulario del Libro de Reclamaciones
    @GetMapping("/public/libroReclamaciones")
    public String mostrarFormulario(Model model) {
        model.addAttribute("libroReclamaciones", new LibroReclamaciones());
        return "libroReclamaciones";
    }

    @PostMapping("/public/enviarReclamacion")
    public String enviarReclamacion(@ModelAttribute LibroReclamaciones libroReclamaciones, Model model) {
        model.addAttribute("libroReclamaciones", libroReclamaciones);
        return "envioLibroReclamaciones";
    }

    // Formulario para contactanos
    @GetMapping("/public/contactanos")
    public String mostrarContactanos(Model model) {
        model.addAttribute("contacto", new Contactanos());
        return "contactanos";
    }

    @PostMapping("/public/enviarConsulta")
    public String enviarConsulta(@ModelAttribute Contactanos consulta, Model model) {
        model.addAttribute("contacto", consulta);
        return "envioContactanos";
    }

    // Galeria de Productos
    @GetMapping("/public/galeria")
    public String galeriaProductos(Model model) {
        List<Producto> lista = productoService.listarProductos();
        model.addAttribute("productos", lista);
        return "galeria";
    }

    // Mostrar Productos de una misma Categoria
    @GetMapping("/public/productos/categoria/{id}")
    public String productosPorCategoria(@PathVariable Integer id, Model model) {
        List<Producto> productosPorCategoria = productoService.listarProductosPorCategoria(id);
        model.addAttribute("productos", productosPorCategoria);
        return "galeria";
    }
}

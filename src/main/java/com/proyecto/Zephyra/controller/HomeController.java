package com.proyecto.Zephyra.controller;

import com.proyecto.Zephyra.entidades.Producto;
import com.proyecto.Zephyra.model.Contactanos;
import com.proyecto.Zephyra.model.Devolucion;
import com.proyecto.Zephyra.model.LibroReclamaciones;
import com.proyecto.Zephyra.model.Sugerencia;
import com.proyecto.Zephyra.repositorios.CategoriaRepository;
import com.proyecto.Zephyra.repositorios.MarcaRepository;
import com.proyecto.Zephyra.repositorios.ProductoRepository;
import com.proyecto.Zephyra.servicios.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping({ "/", "/index" })
    public String listarProductos(Model model) {
        List<Producto> lista = productoService.listarProductos();
        List<Producto> productosDestacados = productoService.listarProductosPorCategoria((long) 7);
        List<Producto> proximosLanzamientos = productoService.listarProductosPorCategoria((long) 6);
        model.addAttribute("productos", lista);
        model.addAttribute("productosDestacados", productosDestacados);
        model.addAttribute("proximosLanzamientos", proximosLanzamientos);
        return "index";
    }

    @GetMapping("/detalleProducto/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        Producto producto = productoService.obtenerProductoPorId(id);
        if (producto != null) {
            model.addAttribute("producto", producto);
            List<Producto> productosDestacados = productoService.listarProductosPorCategoria((long) 7);
            model.addAttribute("productosDestacados", productosDestacados);
            return "detalleProducto";
        } else {
            return "redirect:/index"; // Redirige si no se encuentra el colaborador
        }
    }

    @GetMapping("/editarCarrito")
    public String editarCarrito() {
        return "editarCarrito"; // Muestra la pagina html
    }

    @GetMapping("/paginaPago")
    public String paginaPago() {
        return "paginaPago"; // Muestra la pagina html
    }

    @GetMapping("/politicaEnvios")
    public String politicaEnvios() {
        return "politicaEnvios"; // Muestra la pagina html
    }

    @GetMapping("/politicaDevoluciones")
    public String politicaDevoluciones() {
        return "politicaDevoluciones"; // Muestra la pagina html
    }

    @GetMapping("/preguntasFrecuentes")
    public String preguntasFrecuentes() {
        return "preguntasFrecuentes"; // Muestra la pagina html
    }

    @GetMapping("/servicioEntrega")
    public String servicioEntrega() {
        return "servicioEntrega"; // Muestra la pagina html
    }

    // Formulario de
    // Sugerencia------------------------------------------------------------------------------------
    @GetMapping("/sugerencia")
    public String mostrarSugerencia(Model model) {
        model.addAttribute("sugerencia", new Sugerencia());
        return "formularioSugerencia";
    }

    @PostMapping("/sugerencia")
    public String enviarSugerencia(@ModelAttribute Sugerencia sugerencia, Model model) {
        // Aquí se puede procesar la sugerencia o guardarla en la base de datos
        model.addAttribute("sugerencia", sugerencia);
        return "envioSugerencia";
    }

    // Formulario de
    // devolucion-------------------------------------------------------------------------------------
    @GetMapping("/formularioDevolucion")
    public String mostrarDevolucion(Model model) {
        model.addAttribute("devolucion", new Devolucion());
        return "formularioDevolucion";
    }

    @PostMapping("/envioDevolucion")
    public String enviarDevolucion(@ModelAttribute Devolucion devolucion, Model model) {
        model.addAttribute("devolucion", devolucion);
        return "envioDevolucion";
    }

    // Formulario del Libro de
    // Reclamaciones--------------------------------------------------------------------------
    @GetMapping("/libroReclamaciones")
    public String mostrarFormulario(Model model) {
        model.addAttribute("libroReclamaciones", new LibroReclamaciones());
        return "libroReclamaciones";
    }

    @PostMapping("/enviarReclamacion")
    public String enviarReclamacion(@ModelAttribute LibroReclamaciones libroReclamaciones, Model model) {
        model.addAttribute("libroReclamaciones", libroReclamaciones);
        return "envioLibroReclamaciones";
    }

    // Formulario para contactanos
    @GetMapping("/contactanos")
    public String mostrarContactanos(Model model) {
        model.addAttribute("contacto", new Contactanos());
        return "contactanos"; // Nombre de la vista del formulario
    }

    @PostMapping("/enviarConsulta")
    public String enviarConsulta(@ModelAttribute Contactanos consulta, Model model) {
        model.addAttribute("contacto", consulta);
        return "envioContactanos"; // Nombre de la vista de resultado
    }

    // Galeria de Productos
    @GetMapping("/galeria")
    public String galeriaProductos(Model model) {
        List<Producto> lista = productoService.listarProductos();
        model.addAttribute("productos", lista);
        return "galeria";
    }

    // Administrador
    // @GetMapping("/ADM")
    // public String abirAdministrador() {
    //     return "ADM_index";
    // }










    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @GetMapping("/ADM")
    public String mostrarDashboard(Model model) {
        long totalProductos = productoRepository.count();
        long totalCategorias = categoriaRepository.count();
        long totalMarcas = marcaRepository.count();

        model.addAttribute("totalProductos", totalProductos);
        model.addAttribute("totalCategorias", totalCategorias);
        model.addAttribute("totalMarcas", totalMarcas);
        
        return "ADM_index"; // Nombre de la vista
    }
}

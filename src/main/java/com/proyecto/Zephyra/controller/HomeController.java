package com.proyecto.Zephyra.controller;

import com.proyecto.Zephyra.model.Contactanos;
import com.proyecto.Zephyra.model.Devolucion;
import com.proyecto.Zephyra.model.LibroReclamaciones;
import com.proyecto.Zephyra.model.Sugerencia;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    @GetMapping("/detalleProducto")
    public String detalleProducto() {
        return "detalleProducto";  // Muestra la pagina html
    }

    @GetMapping("/editarCarrito")
    public String editarCarrito() {
        return "editarCarrito";  // Muestra la pagina html
    }

    @GetMapping("/index")
    public String index() {
        return "index";  // Muestra la pagina html
    }

    @GetMapping("/paginaPago")
    public String paginaPago() {
        return "paginaPago";  // Muestra la pagina html
    }

    @GetMapping("/politicaEnvios")
    public String politicaEnvios() {
        return "politicaEnvios";  // Muestra la pagina html
    }

    @GetMapping("/politicaDevoluciones")
    public String politicaDevoluciones() {
        return "politicaDevoluciones";  // Muestra la pagina html
    }

    @GetMapping("/preguntasFrecuentes")
    public String preguntasFrecuentes() {
        return "preguntasFrecuentes";  // Muestra la pagina html
    }

    @GetMapping("/servicioEntrega")
    public String servicioEntrega() {
        return "servicioEntrega";  // Muestra la pagina html
    }

    // Formulario de Sugerencia------------------------------------------------------------------------------------
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

    //Formulario de devolucion-------------------------------------------------------------------------------------
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

    //Formulario del Libro de Reclamaciones--------------------------------------------------------------------------
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

    //Formulario para contactanos
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
}

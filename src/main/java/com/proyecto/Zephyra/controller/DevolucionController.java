package com.proyecto.Zephyra.controller;

import com.proyecto.Zephyra.model.Devolucion;
import com.proyecto.Zephyra.servicios.CDevolucionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DevolucionController {

    @Autowired
    private CDevolucionServicio servicio;

    @GetMapping({"/DevolucionListas"})
    public String listarDevoluciones(Model modelo) {
        modelo.addAttribute("DevolucionListas", servicio.listarTodasLasDevoluciones());
        return "DevolucionListas"; // Retorna a la vista ListasDeDevoluciones
    }

    @GetMapping("/DevolucionListas/nuevo")
    public String mostrarFormularioDeRegistrarDevolucion(Model modelo) {
        Devolucion devolucion = new Devolucion();
        modelo.addAttribute("devolucion", devolucion);
        return "CrearformularioDevolucion"; // Retorna a la vista CreaDevolucion
    }

    @PostMapping("/DevolucionListas")
    public String guardarDevolucion(@ModelAttribute("devolucion") Devolucion devolucion) {
        servicio.guardarDevolucion(devolucion);
        return "redirect:/ ";
    }

    //@GetMapping("/DevolucionListas/editar/{id}")
    // public String mostrarFormularioDeEditar(@PathVariable Long id, Model modelo) {
    //    modelo.addAttribute("devolucion", servicio.obtenerDevolucionPorId(id));
    //    return "EditarDevolucion"; // Retorna a la vista EditarDevolucion
    //}
    
    
    @PostMapping("/DevolucionListas/{id}")
    public String actualizarDevolucion(@PathVariable Long id, @ModelAttribute("devolucion") Devolucion devolucion,
            Model modelo) {
        // Obtener la devolucion existente por ID
        Devolucion devolucionExistente = servicio.obtenerDevolucionPorId(id);
        devolucionExistente.setId(id);
        devolucionExistente.setNombreCompleto(devolucion.getNombreCompleto());
        devolucionExistente.setApellidoCompleto(devolucion.getApellidoCompleto());
        devolucionExistente.setCorreoElectronico(devolucion.getCorreoElectronico());
        devolucionExistente.setNumeroTelefono(devolucion.getNumeroTelefono());
        devolucionExistente.setMotivoDevolucion(devolucion.getMotivoDevolucion());
        devolucionExistente.setEvidencia(devolucion.getEvidencia());
        
        servicio.actualizarDevolucion(devolucionExistente);
        return "redirect:/DevolucionListas";
    }

    @GetMapping("/DevolucionListas/{id}")
    public String eliminarDevolucion(@PathVariable Long id) {
        servicio.eliminarDevolucion(id);
        return "redirect:/DevolucionListas";
    }

}

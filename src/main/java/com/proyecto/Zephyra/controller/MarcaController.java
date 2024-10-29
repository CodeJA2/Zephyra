package com.proyecto.Zephyra.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.proyecto.Zephyra.entidades.Marca;
import com.proyecto.Zephyra.servicios.MarcaService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    // C - Create (Crear):
    @GetMapping("/ADM/marcas/nuevo")
    public String crearMarcaForm(Model model) {
        model.addAttribute("marca", new Marca());
        return "/ADM_crearMarca";
    }

    // R - Read (Leer):
    @GetMapping("/ADM/marcas")
    public String listarMarcas(Model model) {
        List<Marca> lista = marcaService.listarMarcas();
        model.addAttribute("marcas", lista);
        return "ADM_listarMarcas";
    }

    @PostMapping("/ADM/marcas")
    public String guardarMarca(@ModelAttribute("marca") Marca marca) {
        marcaService.guardarMarca(marca);
        return "redirect:/ADM/marcas";
    }

    // U - Update (Actualizar):
    @GetMapping("/ADM/marcas/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        Marca marca = marcaService.obtenerMarcaPorId(id);
        if (marca != null) {
            model.addAttribute("marca", marca);
            return "ADM_editarMarca";
        } else {
            return "redirect:/ADM/marcas";
        }
    }

    @PostMapping("/ADM/marcas/actualizar/{id}")
    public String actualizarMarca(@PathVariable("id") Long id,
            @ModelAttribute("marca") Marca marcaActualizado) {
        Marca marcaExistente = marcaService.obtenerMarcaPorId(id);
        if (marcaExistente != null) {
            marcaExistente.setNombre(marcaActualizado.getNombre());
            marcaService.actualizarMarca(marcaExistente);
        }
        return "redirect:/ADM/marcas";
    }

    // D - Delete (Eliminar):
    @GetMapping("/ADM/marcas/eliminar/{id}")
    public String eliminarMarca(@PathVariable("id") Long id) {
        marcaService.eliminarMarca(id);
        return "redirect:/ADM/marcas";
    }
}

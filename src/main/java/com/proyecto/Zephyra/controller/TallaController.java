package com.proyecto.Zephyra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyecto.Zephyra.model.Talla;
import com.proyecto.Zephyra.servicios.TallaService;

@Controller
public class TallaController {
    
     @Autowired
    private TallaService tallaService;

    // C - CREATE (Crear talla):
    @GetMapping("/ADM/tallas/nuevo")
    public String crearTalla(Model model) {
        model.addAttribute("talla", new Talla());
        return "ADM_crearTalla";
    }

    @PostMapping("/ADM/tallas")
    public String guardarTalla(@ModelAttribute("talla") Talla talla) {
        tallaService.guardarTalla(talla);
        return "redirect:/ADM/tallas";
    }

    // R - READ (Leer talla):
    @GetMapping("/ADM/tallas")
    public String listarTallas(Model model) {
        List<Talla> listaTallas = tallaService.listarTallas();
        model.addAttribute("listaTallas", listaTallas);
        return "ADM_listarTallas";
    }

    // U - UPDATE (Actualizar talla):
    @GetMapping("/ADM/tallas/editar/{id}")
    public String editarTalla(@PathVariable("id") Integer id, Model model) {
        Talla talla = tallaService.obtenerTallaPorId(id);
        if (talla != null) {
            model.addAttribute("talla", talla);
            return "ADM_editarTalla";
        } else {
            return "redirect:/ADM/tallas";
        }
    }

    @PostMapping("/ADM/tallas/actualizar/{id}")
    public String actualizarTalla(@PathVariable("id") Integer id,
            @ModelAttribute("talla") Talla tallaActualizada) {
        Talla tallaExistente = tallaService.obtenerTallaPorId(id);
        if (tallaExistente != null) {
            tallaExistente.setStock(tallaActualizada.getStock());
            tallaExistente.setTamaño(tallaActualizada.getTamaño());
            tallaExistente.setProducto(tallaActualizada.getProducto());
            tallaService.actualizarTalla(tallaExistente);
        }
        return "redirect:/ADM/tallas";
    }

    // D - DELETE (Eliminar talla):
    @GetMapping("/ADM/tallas/eliminar/{id}")
    public String eliminarTalla(@PathVariable("id") Integer id) {
        tallaService.eliminarTalla(id);
        return "redirect:/ADM/tallas";
    }

}

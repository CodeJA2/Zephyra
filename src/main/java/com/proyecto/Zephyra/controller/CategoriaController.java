package com.proyecto.Zephyra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.proyecto.Zephyra.entidades.Categoria;
import com.proyecto.Zephyra.servicios.CategoriaService;

import java.util.List;

@Controller
// @RequestMapping("/productos")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    // C - Create (Crear):
    @GetMapping("/categorias/nuevo")
    public String crearCategoria(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "ADM_crearCategoria";
    }

    // R - Read (Leer):
    @GetMapping("/categorias")
    public String listarCategorias(Model model) {
        List<Categoria> lista = categoriaService.listarCategorias();
        model.addAttribute("categorias", lista);
        return "ADM_listarCategorias";
    }

    @PostMapping("/categorias")
    public String guardarCategoria(@ModelAttribute("categoria") Categoria categoria) {
        categoriaService.guardarCategoria(categoria);
        return "redirect:/categorias";
    }

    // U - Update (Actualizar):
    @GetMapping("/categorias/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        Categoria categoria = categoriaService.obtenerCategoriaPorId(id);
        if (categoria != null) {
            model.addAttribute("categoria", categoria);
            return "ADM_editarCategoria";
        } else {
            return "redirect:/categorias"; // Redirige si no se encuentra el colaborador
        }
    }

    @PostMapping("/categorias/actualizar/{id}")
    public String actualizarCategoria(@PathVariable("id") Long id,
            @ModelAttribute("categoria") Categoria categoriaActualizado) {
        Categoria categoriaExistente = categoriaService.obtenerCategoriaPorId(id);
        if (categoriaExistente != null) {
            categoriaExistente.setNombre(categoriaActualizado.getNombre());
            categoriaService.actualizarCategoria(categoriaExistente);
        }
        return "redirect:/categorias";
    }

    // D - Delete (Eliminar):
    @GetMapping("/categorias/eliminar/{id}")
    public String eliminarCategoria(@PathVariable("id") Long id) {
        categoriaService.eliminarCategoria(id);
        return "redirect:/categorias";
    }
    
}

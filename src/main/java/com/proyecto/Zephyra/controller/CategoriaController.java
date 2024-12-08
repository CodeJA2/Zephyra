package com.proyecto.Zephyra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.proyecto.Zephyra.model.Categoria;
import com.proyecto.Zephyra.servicios.CategoriaService;
import java.util.List;

@Controller
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // C - CREATE (Crear categoria):
    @GetMapping("/ADM/categorias/nuevo")
    public String crearCategoria(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "ADM_crearCategoria";
    }

    @PostMapping("/ADM/categorias")
    public String guardarCategoria(@ModelAttribute("categoria") Categoria categoria) {
        categoriaService.guardarCategoria(categoria);
        return "redirect:/ADM/categorias";
    }

    // R - READ (Leer categoria):
    @GetMapping("/ADM/categorias")
    public String listarCategorias(Model model) {
        List<Categoria> lista = categoriaService.listarCategorias();
        model.addAttribute("listaCategorias", lista);
        return "ADM_listarCategorias";
    }

    // U - UPDATE (Actualizar categoria):
    @GetMapping("/ADM/categorias/editar/{id}")
    public String editarCategoria(@PathVariable("id") Long id, Model model) {
        Categoria categoria = categoriaService.obtenerCategoriaPorId(id);
        if (categoria != null) {
            model.addAttribute("categoria", categoria);
            return "ADM_editarCategoria";
        } else {
            return "redirect:/ADM/categorias";
        }
    }

    @PostMapping("/ADM/categorias/actualizar/{id}")
    public String actualizarCategoria(@PathVariable("id") Long id,
            @ModelAttribute("categoria") Categoria categoriaActualizado) {
        Categoria categoriaExistente = categoriaService.obtenerCategoriaPorId(id);
        if (categoriaExistente != null) {
            categoriaExistente.setNombre(categoriaActualizado.getNombre());
            categoriaExistente.setPara(categoriaActualizado.getPara());
            categoriaService.actualizarCategoria(categoriaExistente);
        }
        return "redirect:/ADM/categorias";
    }

    // D - DELETE (Eliminar categoria):
    @GetMapping("/ADM/categorias/eliminar/{id}")
    public String eliminarCategoria(@PathVariable("id") Long id) {
        categoriaService.eliminarCategoria(id);
        return "redirect:/ADM/categorias";
    }

}

package com.proyecto.Zephyra.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.Zephyra.DTO.CarritoRequest;
import com.proyecto.Zephyra.model.Carrito;
import com.proyecto.Zephyra.model.CarritoItem;
import com.proyecto.Zephyra.model.User;
import com.proyecto.Zephyra.servicios.CarritoService;

@Controller
@RequestMapping("/public/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @ModelAttribute("usuario")
    public User obtenerUsuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal(); // Retorna el usuario autenticado
        }
        return null; // Retorna null si no hay usuario autenticado
    }

    @PostMapping("/agregar")
    public ResponseEntity<String> agregarProductoAlCarrito(
            @RequestBody CarritoRequest carritoRequest,
            @ModelAttribute("usuario") User usuario) {
        try {
            carritoService.agregarProductoAlCarrito(usuario, carritoRequest.getProductoId(),
                    carritoRequest.getTallaId(), carritoRequest.getCantidad());
            return ResponseEntity.ok("Producto agregado al carrito exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/ver")
    public String verCarrito(Model model, @ModelAttribute("usuario") User usuario) {
        if (usuario != null) {
            Carrito carrito = carritoService.obtenerCarritoPorUsuario(usuario);
            if (carrito != null) {
                List<CarritoItem> items = carritoService.obtenerItemsDelCarrito(usuario);
                model.addAttribute("items", items);
                double total = carritoService.calcularTotalCarrito(usuario); // Calcula el total
                model.addAttribute("total", total);
            } else {
                model.addAttribute("items", Collections.emptyList());
                model.addAttribute("total", 0.0);
            }
        }
        return "carrito";
    }

    @PostMapping("/eliminar/{itemId}")
    public String eliminarProductoDelCarrito(@PathVariable Integer itemId, @ModelAttribute("usuario") User usuario) {
        try {
            carritoService.eliminarProductoDelCarrito(itemId);
            return "redirect:/public/carrito/ver"; // Redirige a la vista del carrito
        } catch (RuntimeException e) {
            // Maneja el error, podrías agregar un mensaje de error en la vista
            return "error";
        }
    }

    @PostMapping("/disminuir/{itemId}")
    public String disminuirCantidadProducto(@PathVariable Integer itemId, @ModelAttribute("usuario") User usuario) {
        try {
            carritoService.disminuirCantidadProducto(itemId);
            return "redirect:/public/carrito/ver"; // Redirige a la vista del carrito
        } catch (RuntimeException e) {
            // Maneja el error, podrías agregar un mensaje de error en la vista
            return "redirect:/public/carrito/ver";
        }
    }

    @PostMapping("/aumentar/{itemId}")
    public String aumentarCantidadProducto(@PathVariable Integer itemId, @ModelAttribute("usuario") User usuario) {
        try {
            carritoService.aumentarCantidadProducto(itemId);
            return "redirect:/public/carrito/ver"; // Redirige a la vista del carrito
        } catch (RuntimeException e) {
            // Maneja el error, podrías agregar un mensaje de error en la vista
            return "redirect:/public/carrito/ver";
        }
    }

}

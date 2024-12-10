package com.proyecto.Zephyra.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.proyecto.Zephyra.DTO.VentaDTO;
import com.proyecto.Zephyra.model.User;
import com.proyecto.Zephyra.model.Venta;
import com.proyecto.Zephyra.servicios.VentaService;

@Controller
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @ModelAttribute("usuario")
    public User obtenerUsuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal(); 
        }
        return null; 
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crearVenta(@RequestBody VentaDTO ventaDTO, @ModelAttribute("usuario") User usuario) {
        try {
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autenticado");
            }
            // Asociar usuario autenticado a la venta
            ventaDTO.setUserId(usuario.getId());
    
            // Registrar venta con detalles
            ventaService.crearVenta(ventaDTO); 
    
            return ResponseEntity.ok("Venta registrada con éxito");
        } catch (Exception e) {
            e.printStackTrace(); // Imprime el error en logs para verificar
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar la venta");
        }
    }

    @GetMapping("/pagoExitoso")
    public String mostrarPaginaExito() {
        return "pagoExitoso"; // Redirige a la vista de éxito del pago
    }

    // R - READ (Leer producto):
    @GetMapping("/ADM/ventas")
    public String listarVentas(Model model) {
        List<Venta> lista = ventaService.listarVentas();
        model.addAttribute("ventas", lista);
        return "ADM_listarVentas";
    }
}

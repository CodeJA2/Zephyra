package com.proyecto.Zephyra.controller;


import com.proyecto.Zephyra.servicios.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Método para registrar al usuario y redirigir a la página de inicio
    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
            @RequestParam String password,
            @RequestParam String dni,
            @RequestParam String fullName,
            @RequestParam String address,
            @RequestParam String phoneNumber,
            Model model) {
        try {
            // Asignar el rol "USER" por defecto
            String role = "USER"; // El rol por defecto será "USER"

            // Llamamos al servicio para registrar al usuario
            usuarioService.createUser(username, password, role, dni, fullName, address, phoneNumber);

            // Si el registro es exitoso, redirigir a index.html
            return "redirect:/"; // Redirige a la página principal (index.html)
        } catch (Exception e) {
            model.addAttribute("error", "Hubo un problema al registrar el usuario");
            return "register"; // Redirige de vuelta al formulario de registro con un error
        }
    }



}

package com.proyecto.Zephyra.Auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        LoginRequest request = new LoginRequest(username, password);
        AuthResponse authResponse = authService.login(request);

        if (authResponse != null) {
            model.addAttribute("user", authResponse); // Guarda el usuario en la sesión
            return "redirect:/index"; // Redirige a la página principal
        } else {
            return "redirect:/auth/login?error"; // Redirige de nuevo a login con un parámetro de error
        }
    }

    @GetMapping("/logout") // Ruta para cerrar sesión
    public String logout(Model model) {
        model.asMap().remove("user"); // Elimina el usuario de la sesión
        return "redirect:/auth/login"; // Redirige a la página de login
    }

    @PostMapping(value = "register")
    public String register(
            @RequestParam String username,
            @RequestParam String dni,
            @RequestParam String password,
            @RequestParam String fullName,
            @RequestParam String address,
            @RequestParam String phoneNumber,
            Model model) {

        RegisterRequest request = new RegisterRequest(username, dni, password, fullName, address, phoneNumber);
        AuthResponse authResponse = authService.register(request);

        if (authResponse != null) {
            model.addAttribute("user", authResponse); // Guarda el usuario en la sesión
            return "redirect:/index"; // Redirige a la página principal
        } else {
            return "redirect:/auth/register?error"; // Redirige de nuevo a registro con un parámetro de error
        }
    }


    
}

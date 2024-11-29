package com.proyecto.Zephyra.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.Zephyra.model.User;
import com.proyecto.Zephyra.servicios.UsuarioService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/ADM/usuarios")
@RequiredArgsConstructor
public class UsuariosController {

    private final UsuarioService usuarioService;

    @GetMapping
    public String mostrarUsuarios(Model model) {
        List<User> users = usuarioService.getAllUsers();
        model.addAttribute("users", users);
        return "ADM_usuarios";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "ADM_crearUsuario";
    }

    @GetMapping("/edit/{id}")
    public String showEditPage(@PathVariable Integer id, Model model) {
        User user = usuarioService.getUser(id);
        model.addAttribute("user", user);
        return "ADM_editarUsuario";
    }


    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        usuarioService.deleteUser(id);
        return "redirect:/ADM/usuarios";
    }

    @PostMapping("/register")
    public String registerUserFromAdmin(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String role,
            @RequestParam String dni,
            @RequestParam String fullName,
            @RequestParam String address,
            @RequestParam String phoneNumber,
            Model model) {
        try {
            // Validar que el rol sea válido (opcional)
            if (!role.equals("USER") && !role.equals("ADMIN")) {
                throw new IllegalArgumentException("Rol inválido: " + role);
            }

            // Registrar al usuario
            usuarioService.createUser(username, password, role, dni, fullName, address, phoneNumber);

            // Redirigir a la lista de usuarios si el registro es exitoso
            return "redirect:/ADM/usuarios";
        } catch (Exception e) {
            model.addAttribute("error", "Hubo un problema al registrar el usuario: " + e.getMessage());
            return "ADM_crearUsuario"; // Volver a la vista de creación en caso de error
        }
    }

    @PostMapping("/edit/{id}")
    public String updateUser(
            @RequestParam Integer id,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String role,
            @RequestParam String dni,
            @RequestParam String fullName,
            @RequestParam String address,
            @RequestParam String phoneNumber,
            Model model) {
        try {
            // Llamar al servicio para actualizar el usuario
            usuarioService.updateUser(id, username, password, role, dni, fullName, address, phoneNumber);

            // Redirigir a la lista de usuarios después de actualizar
            return "redirect:/ADM/usuarios";
        } catch (Exception e) {
            model.addAttribute("error", "Hubo un problema al actualizar el usuario");
            return "admin/editUser"; // Redirigir al formulario de edición con el error
        }
    }
}

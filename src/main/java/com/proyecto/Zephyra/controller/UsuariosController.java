package com.proyecto.Zephyra.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.Zephyra.User.UserDTO;
import com.proyecto.Zephyra.User.UserRequest;
import com.proyecto.Zephyra.User.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/ADM/usuarios")
@RequiredArgsConstructor
public class UsuariosController {

    private final UserService userService;

    @GetMapping
    public String mostrarUsuarios(Model model) {
        List<UserDTO> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "ADM_usuarios";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "ADM_crearUsuario"; 
    }

   
    @GetMapping("/edit/{id}")
    public String showEditPage(@PathVariable Integer id, Model model) {
        UserDTO user = userService.getUser(id);
        model.addAttribute("user", user);
        return "ADM_editarUsuario"; 
    }


    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Integer id, @ModelAttribute UserRequest userRequest) {
        userRequest.setId(id); 
        userService.updateUser(userRequest); 
        return "redirect:/ADM/usuarios"; 
    }

    
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id); 
        return "redirect:/ADM/usuarios"; 
    }


}

package com.proyecto.Zephyra.servicios;


import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.Zephyra.model.Carrito;
import com.proyecto.Zephyra.model.User;
import com.proyecto.Zephyra.repositorios.CarritoRepository;
import com.proyecto.Zephyra.repositorios.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CarritoRepository carritoRepository;

    public void createUser(String username, String password, String role, String dni, String fullName, String address,
            String phoneNumber) {
        // Codificar la contraseña
        String encodedPassword = passwordEncoder.encode(password);

        // Crear el usuario con todos los campos necesarios
        User usuario = new User();
        usuario.setUsername(username);
        usuario.setPassword(encodedPassword);
        usuario.setRole(role);
        usuario.setDni(dni);
        usuario.setFullName(fullName);
        usuario.setAddress(address);
        usuario.setPhoneNumber(phoneNumber);

        userRepository.save(usuario);

        // Crear y asociar un carrito al usuario
        Carrito carrito = new Carrito();
        carrito.setUsuario(usuario); // Asignar el usuario al carrito
        carritoRepository.save(carrito);

    }

    public void updateUser(Integer id, String username, String password, String role, String dni, String fullName,
            String address,
            String phoneNumber) {
        // Buscar al usuario por ID
        User usuario = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Codificar la contraseña
        String encodedPassword = passwordEncoder.encode(password);
        // Actualizar los campos del usuario
        usuario.setId(id);
        usuario.setUsername(username);
        usuario.setPassword(encodedPassword);
        usuario.setRole(role);
        usuario.setDni(dni);
        usuario.setFullName(fullName);
        usuario.setAddress(address);
        usuario.setPhoneNumber(phoneNumber);

        // Guardar los cambios
        userRepository.save(usuario);
    }

    public User getUser(Integer userId) {
        // Buscar y devolver al usuario por ID
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public List<User> getAllUsers() {
        // Obtener y devolver todos los usuarios
        return userRepository.findAll();
    }

    public void deleteUser(Integer userId) {
        // Verificar si el usuario existe y eliminarlo
        User usuario = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        userRepository.delete(usuario);
    }
}

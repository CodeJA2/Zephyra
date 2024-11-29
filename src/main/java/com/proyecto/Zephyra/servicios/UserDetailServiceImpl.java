package com.proyecto.Zephyra.servicios;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.proyecto.Zephyra.model.User;
import com.proyecto.Zephyra.repositorios.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

    private final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar el usuario por el nombre de usuario
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con nombre de usuario: " + username));

        // Si el usuario es encontrado, devolver una instancia de SecurityUser (o UserDetails)
        return user;
    }
    
}

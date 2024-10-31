package com.proyecto.Zephyra.Auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.Zephyra.User.Role;
import com.proyecto.Zephyra.User.User;
import com.proyecto.Zephyra.User.UserRepository;
import com.proyecto.Zephyra.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        String token = jwtService.getToken(user, user.getId());
        return AuthResponse.builder()
        .token(token)
        .userId(user.getId())
        .build();
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
        .username(request.getUsername())
        .dni(request.getDni())
        .password(passwordEncoder.encode(request.getPassword()))
        .fullName(request.getFullName())
        .address(request.getAddress())
        .phoneNumber(request.getPhoneNumber())
        .role(Role.USER)
        .build();

        userRepository.save(user);

        Integer userId = user.getId();
        
        return AuthResponse.builder()
        .token(jwtService.getToken(user, userId))
        .userId(userId)
        .build();
    }
}
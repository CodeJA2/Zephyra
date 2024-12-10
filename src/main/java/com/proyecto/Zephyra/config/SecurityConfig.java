package com.proyecto.Zephyra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public HttpSessionEventPublisher httpSessionEventPublisher() {
                return new HttpSessionEventPublisher();
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http
                                .csrf(csrf -> csrf
                                                .disable()) // Desactiva CSRF si no es necesario
                                .authorizeHttpRequests(authRequest -> authRequest
                                                .requestMatchers(HttpMethod.POST).permitAll()
                                                .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.OPTIONS).permitAll()
                                                .requestMatchers("/").permitAll()
                                                .requestMatchers("/public/**", "/css/**", "/js/**", "/img/**")
                                                .permitAll()
                                                .requestMatchers("/ADM/**").permitAll()//hasRole("ADMIN")
                                                .anyRequest().authenticated())
                                .formLogin(form -> form
                                                .defaultSuccessUrl("/ADM", true) // URL de éxito después de iniciar
                                                                                 // sesión
                                                .failureHandler((request, response, exception) -> {
                                                        response.sendRedirect("/"); // Redirige en caso
                                                                                    // de error
                                                }))
                                .logout(logout -> logout
                                                .logoutUrl("/logout") // URL de logout
                                                .logoutSuccessUrl("/login"))

                                .exceptionHandling(exception -> exception
                                                .accessDeniedHandler((request, response, accessDeniedException) -> {
                                                        response.sendRedirect("/"); // Redirige a la página

                                                }))
                                .build();
        }
}
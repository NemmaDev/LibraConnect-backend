package com.example.LibraConnect.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors().and()  // Permettre CORS
            .csrf().disable()
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/login", "/api/user","/api/userList","/api/user/me","/api/user/update/{id}",
                                 "/api/Usercount","/api/user/delete/{id}","/api/categories","/api/books",
                                 "/api/books/delete/{id}","/api/books/update/{id}","/api/books/count",
                                 "/api/books/add","/api/books/bookList","/api/borrows/borrow","/api/borrows/count",
                                 "/api/borrows/user/{userId}","/api/borrows/all","/api/borrows/return/{id}",
                                 "/api/newsletter/subscribe").permitAll()
                .anyRequest().authenticated()
            );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

   
}

package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void registerUser(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("El usuario ya existe.");
        }

        // Crear el hash de la contraseña
        String passwordHash = passwordEncoder.encode(password);

        // Guardar el usuario en la base de datos
        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(passwordHash);
        userRepository.save(user);
    }
}

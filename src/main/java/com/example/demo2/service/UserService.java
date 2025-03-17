package com.example.demo2.service;

import com.example.demo2.model.dto.UserDTO;
import com.example.demo2.model.dto.UserRequestDTO;
import com.example.demo2.model.entities.UserModel;
import com.example.demo2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static UserDTO mapToDTO(UserModel userSaved) {
        var userDTO = new UserDTO();
        userDTO.setId(userSaved.getId());
        userDTO.setUsername(userSaved.getUsername());
        return userDTO;
    }

    public UserDTO register(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByUsername(userRequestDTO.getUsername())) {
            throw new IllegalArgumentException("El usuario ya existe.");
        }

        var passwordHash = passwordEncoder.encode(userRequestDTO.getPassword());
        userRequestDTO.setPassword(passwordHash);

        var user = mapToEntity(userRequestDTO);
        var userSaved = userRepository.save(user);
        return mapToDTO(userSaved);
    }

    public UserDTO getUserByUsername(String username) {
        var userFounded = userRepository.findByUsername(username);
        return mapToDTO(userFounded);
    }

    private UserModel mapToEntity(UserRequestDTO userRequestDTO) {
        var user = new UserModel();
        user.setUsername(userRequestDTO.getUsername());
        user.setPasswordHash(userRequestDTO.getPassword());
        return user;
    }

}

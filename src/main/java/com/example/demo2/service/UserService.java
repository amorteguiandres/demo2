package com.example.demo2.service;

import com.example.demo2.exceptions.BusinessExceptions;
import com.example.demo2.model.dto.UserDTO;
import com.example.demo2.model.dto.UserRequestDTO;
import com.example.demo2.model.entities.UserModel;
import com.example.demo2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserDTO register(UserRequestDTO userRequestDTO) {
        try {
            validateUserAlreadyExist(userRequestDTO);

            var passwordHash = passwordEncoder.encode(userRequestDTO.getPassword());
            userRequestDTO.setPassword(passwordHash);

            var user = mapToEntity(userRequestDTO);
            var userSaved = userRepository.save(user);
            return mapToDTO(userSaved);

        } catch (Exception e) {
            log.error("Error while registering user", e);
            throw new BusinessExceptions("Error creating user");
        }
    }

    private void validateUserAlreadyExist(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByUsername(userRequestDTO.getUsername())) {
            log.error("Error: Username is already in use");
            throw new IllegalArgumentException("El usuario ya existe.");
        }
    }

    public UserDTO getUserByUsername(String username) {
        var userFounded = Optional.ofNullable(userRepository.findByUsername(username))
                .orElseThrow(() -> new BusinessExceptions("User not found"));
        return mapToDTO(userFounded);
    }

    private UserModel mapToEntity(UserRequestDTO userRequestDTO) {
        var user = new UserModel();
        user.setUsername(userRequestDTO.getUsername());
        user.setPasswordHash(userRequestDTO.getPassword());
        return user;
    }

    private UserDTO mapToDTO(UserModel userSaved) {
        var userDTO = new UserDTO();
        userDTO.setId(userSaved.getId());
        userDTO.setUsername(userSaved.getUsername());
        return userDTO;
    }

}

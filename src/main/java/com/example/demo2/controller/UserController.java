package com.example.demo2.controller;

import com.example.demo2.model.dto.UserDTO;
import com.example.demo2.model.dto.UserRequestDTO;
import com.example.demo2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.register(userRequestDTO);
    }

    @GetMapping("/{username}")
    public UserDTO getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
}

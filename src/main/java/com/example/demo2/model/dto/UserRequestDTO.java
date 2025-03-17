package com.example.demo2.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserRequestDTO extends UserDTO {
    private String password;
}



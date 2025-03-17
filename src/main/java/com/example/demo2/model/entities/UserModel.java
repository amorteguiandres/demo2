package com.example.demo2.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel extends Base implements Serializable {

    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String passwordHash;

}

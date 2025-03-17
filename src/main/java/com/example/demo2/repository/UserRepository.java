package com.example.demo2.repository;

import com.example.demo2.model.entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    boolean existsByUsername(String email);

    //@Query("select c from UserModel c where c.username =:username")
    UserModel findByUsername(String username);
}

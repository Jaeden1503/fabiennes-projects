package com.example.siouxmanagementsystem.persistence;

import com.example.siouxmanagementsystem.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}

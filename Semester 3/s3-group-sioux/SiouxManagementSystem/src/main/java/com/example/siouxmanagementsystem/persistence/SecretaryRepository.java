package com.example.siouxmanagementsystem.persistence;

import com.example.siouxmanagementsystem.persistence.entity.SecretaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SecretaryRepository extends JpaRepository<SecretaryEntity, Long> {
    boolean existsByName(String name);

}

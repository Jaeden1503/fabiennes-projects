package com.example.siouxmanagementsystem.persistence;

import com.example.siouxmanagementsystem.persistence.entity.GuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuestRepository extends JpaRepository<GuestEntity,Long> {

    Optional<GuestEntity> findGuestEntityByLicense(String license);
}

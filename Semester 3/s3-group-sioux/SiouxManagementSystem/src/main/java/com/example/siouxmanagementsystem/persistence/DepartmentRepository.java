package com.example.siouxmanagementsystem.persistence;

import com.example.siouxmanagementsystem.persistence.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {
    DepartmentEntity findDepartmentEntityById(Long depId);
    List<DepartmentEntity> findAll();

}

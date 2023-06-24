package com.example.siouxmanagementsystem.persistence;

import com.example.siouxmanagementsystem.persistence.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
    EmployeeEntity findEmployeeEntityById(Long empId);

    List<EmployeeEntity> getEmployeeEntitiesByDepartment_Name(String departmentName);

    List<EmployeeEntity> findEmployeeEntityByNameContainingIgnoreCase(String name);
}

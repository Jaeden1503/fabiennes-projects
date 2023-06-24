package com.example.siouxmanagementsystem.business.UseCases.EmployeeUseCases;

import com.example.siouxmanagementsystem.persistence.entity.EmployeeEntity;

public interface DeleteEmployeeUseCase {
    void deleteEmployee(Long employeeId);
}

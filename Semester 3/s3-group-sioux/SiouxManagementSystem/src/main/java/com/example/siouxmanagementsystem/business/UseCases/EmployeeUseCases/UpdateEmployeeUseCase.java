package com.example.siouxmanagementsystem.business.UseCases.EmployeeUseCases;

import com.example.siouxmanagementsystem.domain.Employee.UpdateEmployeeRequest;

import javax.transaction.Transactional;

public interface UpdateEmployeeUseCase {
    @Transactional
    void updateEmployee(UpdateEmployeeRequest request);
}

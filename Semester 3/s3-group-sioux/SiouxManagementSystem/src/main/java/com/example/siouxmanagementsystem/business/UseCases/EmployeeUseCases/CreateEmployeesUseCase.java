package com.example.siouxmanagementsystem.business.UseCases.EmployeeUseCases;

import com.example.siouxmanagementsystem.domain.Employee.CreateEmployeesRequest;
import com.example.siouxmanagementsystem.domain.Employee.CreateEmployeesResponse;

public interface CreateEmployeesUseCase {
    CreateEmployeesResponse CreateEmployees(CreateEmployeesRequest request);
}

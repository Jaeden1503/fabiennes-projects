package com.example.siouxmanagementsystem.business.UseCases.EmployeeUseCases;

import com.example.siouxmanagementsystem.domain.Appointment.CreateAppointmentRequest;
import com.example.siouxmanagementsystem.domain.Appointment.CreateAppointmentResponse;
import com.example.siouxmanagementsystem.domain.Employee.CreateEmployeeRequest;
import com.example.siouxmanagementsystem.domain.Employee.CreateEmployeeResponse;

public interface CreateEmployeeUseCase {
    CreateEmployeeResponse CreateEmployee(CreateEmployeeRequest request);
}

package com.example.siouxmanagementsystem.business.impl.Employee;

import com.example.siouxmanagementsystem.business.UseCases.EmployeeUseCases.GetEmployeesUseCase;
import com.example.siouxmanagementsystem.domain.Employee.GetEmployeesResponse;
import com.example.siouxmanagementsystem.persistence.EmployeeRepository;
import com.example.siouxmanagementsystem.persistence.entity.EmployeeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class GetEmployeesUseCaseImpl implements GetEmployeesUseCase {
    private final EmployeeRepository employeeRepository;

    @Transactional
    @Override
    public GetEmployeesResponse getEmployeeEntitiesByDepartmentName(String depName) {
        List<EmployeeEntity> employees = employeeRepository.getEmployeeEntitiesByDepartment_Name(depName)
                .stream()
                .map(EmployeeConverter::convert)
                .toList();

        GetEmployeesResponse response = GetEmployeesResponse.builder().employees(employees).build();

        return response;
    }

    @Transactional
    @Override
    public GetEmployeesResponse findEmployeeEntityByName(String name) {
        List<EmployeeEntity> employee = employeeRepository.findEmployeeEntityByNameContainingIgnoreCase(name);

        GetEmployeesResponse response = GetEmployeesResponse.builder().employees(employee).build();

        return response;
    }
}

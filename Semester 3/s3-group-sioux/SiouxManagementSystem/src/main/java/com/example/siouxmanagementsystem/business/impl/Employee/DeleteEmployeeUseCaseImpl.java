package com.example.siouxmanagementsystem.business.impl.Employee;

import com.example.siouxmanagementsystem.business.UseCases.EmployeeUseCases.DeleteEmployeeUseCase;
import com.example.siouxmanagementsystem.persistence.EmployeeRepository;
import com.example.siouxmanagementsystem.persistence.entity.EmployeeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteEmployeeUseCaseImpl implements DeleteEmployeeUseCase {
    private final EmployeeRepository employeeRepository;

    @Override
    public void deleteEmployee(Long employeeId) {
        EmployeeEntity employee = employeeRepository.findById(employeeId).get();

        if (!employee.getAppointments().isEmpty()) {
            throw new RuntimeException("Employee cannot be deleted!");
        } else if(employee.getAppointments().isEmpty())
        {
            employeeRepository.deleteById(employee.getId());
        }
    }
}

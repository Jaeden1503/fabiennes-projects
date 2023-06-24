package com.example.siouxmanagementsystem.business.impl.Employee;

import com.example.siouxmanagementsystem.business.UseCases.EmployeeUseCases.UpdateEmployeeUseCase;
import com.example.siouxmanagementsystem.domain.Employee.UpdateEmployeeRequest;
import com.example.siouxmanagementsystem.persistence.DepartmentRepository;
import com.example.siouxmanagementsystem.persistence.EmployeeRepository;
import com.example.siouxmanagementsystem.persistence.entity.DepartmentEntity;
import com.example.siouxmanagementsystem.persistence.entity.EmployeeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateEmployeeUseCaseImpl implements UpdateEmployeeUseCase {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Transactional
    @Override
    public void updateEmployee(UpdateEmployeeRequest request) {
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(request.getId());

        EmployeeEntity employee = optionalEmployee.get();
        updateFields(request, employee);
    }

    private void updateFields(UpdateEmployeeRequest request, EmployeeEntity employee) {
        DepartmentEntity departmentEntity = departmentRepository.findById(request.getDepartmentId()).get();

        employee.setName(request.getName());
        employee.setDepartment(departmentEntity);
        employee.setEmailAddress(request.getEmailAddress());

        employeeRepository.save(employee);
    }
}

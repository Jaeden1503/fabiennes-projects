package com.example.siouxmanagementsystem.business.impl.Employee;

import com.example.siouxmanagementsystem.business.UseCases.EmployeeUseCases.CreateEmployeeUseCase;
import com.example.siouxmanagementsystem.business.UseCases.EmployeeUseCases.CreateEmployeesUseCase;
import com.example.siouxmanagementsystem.domain.Employee.*;
import com.example.siouxmanagementsystem.persistence.DepartmentRepository;
import com.example.siouxmanagementsystem.persistence.EmployeeRepository;
import com.example.siouxmanagementsystem.persistence.entity.DepartmentEntity;
import com.example.siouxmanagementsystem.persistence.entity.EmployeeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CreateEmployeesUseCaseImp implements CreateEmployeesUseCase {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Transactional
    @Override
    public CreateEmployeesResponse CreateEmployees(CreateEmployeesRequest request) {
        List<EmployeeEntity> employeeList = new ArrayList();

        for (CreateEmployeeRequest e : request.getEmployees()) {

            DepartmentEntity department= departmentRepository.findDepartmentEntityById(e.getDepartmentId());

            EmployeeEntity employee = EmployeeEntity.builder()
                    .name(e.getName())
                    .emailAddress(e.getEmailAddress())
                    .department(department)
                    .build();


            employeeList.add(employee);
        }

        try {
            List<Long> employeeIds = new ArrayList();
            for (EmployeeEntity employeeEntity : employeeList){
                EmployeeEntity entity = employeeRepository.save(employeeEntity);
                employeeIds.add(employeeEntity.getId());
            }

            CreateEmployeesResponse response = CreateEmployeesResponse.builder().employeeIds(employeeIds).build();
            return response;

        } catch (Exception ex) {
            throw new RuntimeException("Error while creating employees: " + ex.getMessage(), ex);
        }

    }

}

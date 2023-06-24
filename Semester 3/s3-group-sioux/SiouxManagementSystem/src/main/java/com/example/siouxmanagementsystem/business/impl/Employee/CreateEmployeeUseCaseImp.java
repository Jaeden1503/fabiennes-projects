package com.example.siouxmanagementsystem.business.impl.Employee;

import com.example.siouxmanagementsystem.business.UseCases.EmployeeUseCases.CreateEmployeeUseCase;
import com.example.siouxmanagementsystem.domain.Appointment.CreateAppointmentRequest;
import com.example.siouxmanagementsystem.domain.Appointment.CreateAppointmentResponse;
import com.example.siouxmanagementsystem.domain.Employee.CreateEmployeeRequest;
import com.example.siouxmanagementsystem.domain.Employee.CreateEmployeeResponse;
import com.example.siouxmanagementsystem.persistence.DepartmentRepository;
import com.example.siouxmanagementsystem.persistence.EmployeeRepository;
import com.example.siouxmanagementsystem.persistence.entity.AppointmentEntity;
import com.example.siouxmanagementsystem.persistence.entity.DepartmentEntity;
import com.example.siouxmanagementsystem.persistence.entity.EmployeeEntity;
import com.example.siouxmanagementsystem.persistence.entity.GuestEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CreateEmployeeUseCaseImp implements CreateEmployeeUseCase {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Transactional
    @Override
    public CreateEmployeeResponse CreateEmployee(CreateEmployeeRequest request) {

        DepartmentEntity department= departmentRepository.findDepartmentEntityById(request.getDepartmentId());

        EmployeeEntity employee = EmployeeEntity.builder()
                .name(request.getName())
                .emailAddress(request.getEmailAddress())
                .department(department)
                .build();
        try {
            EmployeeEntity entity = employeeRepository.save(employee);
            CreateEmployeeResponse response = CreateEmployeeResponse.builder().employeeId(entity.getId()).build();
            return response;

        } catch (Exception ex) {
            throw new RuntimeException("Error while creating employee: " + ex.getMessage(), ex);
        }
    }
}

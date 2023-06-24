package com.example.siouxmanagementsystem.business.impl.Employee;

import com.example.siouxmanagementsystem.domain.Employee.UpdateEmployeeRequest;
import com.example.siouxmanagementsystem.persistence.DepartmentRepository;
import com.example.siouxmanagementsystem.persistence.EmployeeRepository;
import com.example.siouxmanagementsystem.persistence.entity.DepartmentEntity;
import com.example.siouxmanagementsystem.persistence.entity.EmployeeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateEmployeeUseCaseImplTest {

    @Mock
    private EmployeeRepository employeeRepositoryMock;
    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private UpdateEmployeeUseCaseImpl updateEmployeeUseCase;

    @Test
    void updateEmployee() {
        DepartmentEntity department = DepartmentEntity.builder()
                .id(1L)
                .name("IT")
                .build();

        UpdateEmployeeRequest request = UpdateEmployeeRequest.builder()
                .id(1L)
                .name("test")
                .departmentId(department.getId())
                .emailAddress("test@gmail.com")
                .build();

        EmployeeEntity employee = getEmployee(request);

        when(employeeRepositoryMock.findById(request.getId())).thenReturn(Optional.ofNullable(employee));
        when(departmentRepository.findById(request.getDepartmentId())).thenReturn(Optional.ofNullable(employee.getDepartment()));

        updateEmployeeUseCase.updateEmployee(request);

        verify(employeeRepositoryMock, times(1)).save(employee);
    }

    private EmployeeEntity getEmployee(UpdateEmployeeRequest request) {
        DepartmentEntity department = DepartmentEntity.builder()
                .id(request.getDepartmentId())
                .name("IT")
                .build();

        EmployeeEntity employee = EmployeeEntity.builder()
                .id(request.getId())
                .name("test")
                .department(department)
                .emailAddress("test@gmail.com")
                .build();

        return employee;
    }
}
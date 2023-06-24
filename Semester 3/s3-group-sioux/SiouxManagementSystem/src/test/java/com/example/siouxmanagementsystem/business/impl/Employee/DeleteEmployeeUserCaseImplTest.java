package com.example.siouxmanagementsystem.business.impl.Employee;

import com.example.siouxmanagementsystem.persistence.EmployeeRepository;
import com.example.siouxmanagementsystem.persistence.entity.AppointmentEntity;
import com.example.siouxmanagementsystem.persistence.entity.DepartmentEntity;
import com.example.siouxmanagementsystem.persistence.entity.EmployeeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteEmployeeUserCaseImplTest {

    @Mock
    private EmployeeRepository employeeRepositoryMock;
    @InjectMocks
    private DeleteEmployeeUseCaseImpl deleteEmployeeUseCase;

    @Test
    void deleteEmployee() {
        DepartmentEntity department = DepartmentEntity.builder()
                .id(1L)
                .name("IT")
                .build();

        List<AppointmentEntity> appointments = new ArrayList<>();

        EmployeeEntity employee = EmployeeEntity.builder()
                .id(1L)
                .name("Zin")
                .department(department)
                .emailAddress("zin@gmail.com")
                .appointments(appointments)
                .build();

        when(employeeRepositoryMock.findById(employee.getId())).thenReturn(Optional.of(employee));
        doNothing().when(employeeRepositoryMock).deleteById(employee.getId());

        deleteEmployeeUseCase.deleteEmployee(employee.getId());
        verify(employeeRepositoryMock, times(1)).deleteById(employee.getId());
    }
}
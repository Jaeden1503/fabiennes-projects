package com.example.siouxmanagementsystem.business.impl.Employee;

import com.example.siouxmanagementsystem.domain.Appointment.CreateAppointmentResponse;
import com.example.siouxmanagementsystem.domain.Employee.CreateEmployeeRequest;
import com.example.siouxmanagementsystem.domain.Employee.CreateEmployeeResponse;
import com.example.siouxmanagementsystem.persistence.DepartmentRepository;
import com.example.siouxmanagementsystem.persistence.EmployeeRepository;
import com.example.siouxmanagementsystem.persistence.entity.AppointmentEntity;
import com.example.siouxmanagementsystem.persistence.entity.DepartmentEntity;
import com.example.siouxmanagementsystem.persistence.entity.EmployeeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateEmployeeUseCaseImplTest {

    @Mock
    private EmployeeRepository employeeRepositoryMock;
    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private CreateEmployeeUseCaseImp createEmployeeUseCase;

    @Test
    void createEmployee_withGuestIsNotExist_shouldReturnAppointmentWithNewGuestAdded() {
        DepartmentEntity department = DepartmentEntity.builder()
                .id(1L)
                .name("IT")
                .build();

        CreateEmployeeRequest request = CreateEmployeeRequest.builder()
                .name("Aya")
                .emailAddress("Aya@gmail.com")
                .departmentId(1L)
                .build();

        when(departmentRepository.findDepartmentEntityById(request.getDepartmentId())).thenReturn(department);

        EmployeeEntity employee = EmployeeEntity.builder()
                .id(1L)
                .name("Aya")
                .emailAddress("Aya@gmail.com")
                .department(department)
                .build();

        CreateEmployeeResponse response = CreateEmployeeResponse.builder().employeeId(1L).build();

        when(employeeRepositoryMock.save(any())).thenReturn(employee);

        CreateEmployeeResponse expectedResult = createEmployeeUseCase.CreateEmployee(request);

        assertEquals(response, expectedResult);

        verify(departmentRepository, times(1)).findDepartmentEntityById(request.getDepartmentId());
    }
}
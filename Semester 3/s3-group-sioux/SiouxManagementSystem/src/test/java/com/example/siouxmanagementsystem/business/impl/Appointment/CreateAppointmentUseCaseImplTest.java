package com.example.siouxmanagementsystem.business.impl.Appointment;

import com.example.siouxmanagementsystem.business.impl.Appointment.CreateAppointmentUseCaseImpl;
import com.example.siouxmanagementsystem.domain.Appointment.CreateAppointmentRequest;
import com.example.siouxmanagementsystem.domain.Appointment.CreateAppointmentResponse;
import com.example.siouxmanagementsystem.persistence.AppointmentRepository;
import com.example.siouxmanagementsystem.persistence.DepartmentRepository;
import com.example.siouxmanagementsystem.persistence.EmployeeRepository;
import com.example.siouxmanagementsystem.persistence.GuestRepository;
import com.example.siouxmanagementsystem.persistence.entity.AppointmentEntity;
import com.example.siouxmanagementsystem.persistence.entity.DepartmentEntity;
import com.example.siouxmanagementsystem.persistence.entity.EmployeeEntity;
import com.example.siouxmanagementsystem.persistence.entity.GuestEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateAppointmentUseCaseImplTest {

    @Mock
    private AppointmentRepository appointmentRepositoryMock;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private GuestRepository guestRepository;
    @InjectMocks
    private CreateAppointmentUseCaseImpl createAppointmentUseCase;

    @Test
    void createAppointment_withGuestIsNotExist_shouldReturnAppointmentWithNewGuestAdded() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm");
        DepartmentEntity department = DepartmentEntity.builder()
                .id(1L)
                .name("IT")
                .build();

        EmployeeEntity employee  = EmployeeEntity.builder()
                .id(1L)
                .name("test")
                .department(department)
                .build();

        GuestEntity guest = GuestEntity.builder()
                .id(1L)
                .name("guest")
                .email_address("guest@gmai.com")
                .license("TES123")
                .build();

        CreateAppointmentRequest request = CreateAppointmentRequest.builder()
                .startTime(LocalTime.of(8,00,00).format(formatter))
                .endTime(LocalTime.of(9,00,00).format(formatter))
                .date(LocalDate.of(2023,04,30))
                .employeeId(1L)
                .guestName("test")
                .email("test@gmIl.com")
                .licensePlate("Test123")
                .build();



        when(employeeRepository.findEmployeeEntityById(request.getEmployeeId())).thenReturn(employee);
        when(guestRepository.findGuestEntityByLicense(request.getLicensePlate())).thenReturn(Optional.empty());
        when(guestRepository.save(any())).thenReturn(guest);

        AppointmentEntity Appointment = AppointmentEntity.builder()
                .id(1L)
                .start_time(LocalTime.of(8,00,00).format(formatter))
                .end_time(LocalTime.of(9,00,00).format(formatter))
                .date(LocalDate.of(2023,04,30))
                .employee(employee)
                .guest(guest)
                .build();

        CreateAppointmentResponse response = CreateAppointmentResponse.builder().appointmentId(1L).build();

        when(appointmentRepositoryMock.save(any())).thenReturn(Appointment);

        CreateAppointmentResponse expectedResult = createAppointmentUseCase.CreateAppointment(request);

        assertEquals(response, expectedResult);

        verify(employeeRepository,times(1)).findEmployeeEntityById(request.getEmployeeId());
        verify(guestRepository,times(1)).findGuestEntityByLicense(request.getLicensePlate());
        verify(guestRepository,times(1)).save(any());
    }
    @Test
    void createAppointment_withGuestAlreadyExist_shouldReturnAppointmentWithExistedGuest()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm");

        DepartmentEntity department = DepartmentEntity.builder()
                .id(1L)
                .name("IT")
                .build();

        EmployeeEntity employee  = EmployeeEntity.builder()
                .id(1L)
                .name("test")
                .department(department)
                .build();

        GuestEntity guest = GuestEntity.builder()
                .id(1L)
                .name("guest")
                .email_address("guest@gmai.com")
                .license("TES123")
                .build();

        CreateAppointmentRequest request = CreateAppointmentRequest.builder()
                .startTime(LocalTime.of(8,00,00).format(formatter))
                .endTime(LocalTime.of(9,00,00).format(formatter))
                .date(LocalDate.of(2023,04,30))
                .employeeId(1L)
                .guestName("test")
                .email("test@gmIl.com")
                .licensePlate("Test123")
                .build();

        when(employeeRepository.findEmployeeEntityById(request.getEmployeeId())).thenReturn(employee);
        when(guestRepository.findGuestEntityByLicense(request.getLicensePlate())).thenReturn(Optional.ofNullable(guest));

        AppointmentEntity Appointment = AppointmentEntity.builder()
                .id(1L)
                .start_time(LocalTime.of(8,00,00).format(formatter))
                .end_time(LocalTime.of(9,00,00).format(formatter))
                .date(LocalDate.of(2023,04,30))
                .employee(employee)
                .guest(guest)
                .build();

        CreateAppointmentResponse response = CreateAppointmentResponse.builder().appointmentId(1L).build();

        when(appointmentRepositoryMock.save(any())).thenReturn(Appointment);

        CreateAppointmentResponse expectedResult = createAppointmentUseCase.CreateAppointment(request);

        assertEquals(response, expectedResult);

        verify(employeeRepository,times(1)).findEmployeeEntityById(request.getEmployeeId());
        verify(guestRepository,times(1)).findGuestEntityByLicense(request.getLicensePlate());
        verify(guestRepository,never()).save(any());
    }
}
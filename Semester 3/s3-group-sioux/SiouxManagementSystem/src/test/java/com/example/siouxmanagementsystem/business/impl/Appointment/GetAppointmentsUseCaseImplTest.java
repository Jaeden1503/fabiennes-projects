package com.example.siouxmanagementsystem.business.impl.Appointment;

import com.example.siouxmanagementsystem.business.impl.Appointment.AppointmentConverter;
import com.example.siouxmanagementsystem.business.impl.Appointment.GetAppointmentsUseCaseImpl;
import com.example.siouxmanagementsystem.domain.Appointment.GetAppointmentsResponse;
import com.example.siouxmanagementsystem.persistence.AppointmentRepository;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAppointmentsUseCaseImplTest {
    @Mock
    private AppointmentRepository appointmentRepositoryMock;
    @InjectMocks
    private GetAppointmentsUseCaseImpl getAppointmentsUseCase;

    @Test
    void getAppointmentsByEmpId() {

        AppointmentEntity appointment = getAppointment();
        when(appointmentRepositoryMock.findAppointmentEntitiesByEmployeeId(1L)).thenReturn(List.of(appointment));

        GetAppointmentsResponse actualResponse = getAppointmentsUseCase.getAppointmentsByEmpId(1L);

        GetAppointmentsResponse expectedResponse = GetAppointmentsResponse.builder()
                .appointments(List.of(appointment).stream().map(AppointmentConverter::convert).toList())
                .build();

        assertEquals(actualResponse, expectedResponse);
    }

    @Test
    void getAppointmentsByGuestLicense() {

        AppointmentEntity appointment = getAppointment();
        when(appointmentRepositoryMock.findAllByGuest_License("TES123")).thenReturn(List.of(appointment));

        GetAppointmentsResponse actualResponse = getAppointmentsUseCase.getAppointmentsByGuestLicense("TES123");

        GetAppointmentsResponse expectedResponse = GetAppointmentsResponse.builder()
                .appointments(List.of(appointment).stream().map(AppointmentConverter::convert).toList())
                .build();

        assertEquals(actualResponse, expectedResponse);
    }

    @Test
    void getAppointmentsByGuestName() {

        AppointmentEntity appointment = getAppointment();
        when(appointmentRepositoryMock.findAllByGuest_Name("guest")).thenReturn(List.of(appointment));

        GetAppointmentsResponse actualResponse = getAppointmentsUseCase.getAppointmentsByGuestName("guest");

        GetAppointmentsResponse expectedResponse = GetAppointmentsResponse.builder()
                .appointments(List.of(appointment).stream().map(AppointmentConverter::convert).toList())
                .build();

        assertEquals(actualResponse, expectedResponse);
    }

    @Test
    void getAppointmentsByDate() {
        AppointmentEntity appointment = getAppointment();
        LocalDate startDate = LocalDate.parse("2023-04-01");
        LocalDate endDate = LocalDate.parse("2023-05-01");

        when(appointmentRepositoryMock.findAppointmentEntitiesByDate(startDate, endDate)).thenReturn(List.of(appointment));

        GetAppointmentsResponse actualResponse = getAppointmentsUseCase.getAppointmentsBetweenDates(startDate, endDate);

        GetAppointmentsResponse expectedResponse = GetAppointmentsResponse.builder()
                .appointments(List.of(appointment).stream().map(AppointmentConverter::convert).toList())
                .build();

        assertEquals(actualResponse, expectedResponse);
    }


    private AppointmentEntity getAppointment()
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


        AppointmentEntity appointment = AppointmentEntity.builder()
                .id(1L)
                .start_time(LocalTime.of(8,00,00).format(formatter))
                .end_time(LocalTime.of(9,00,00).format(formatter))
                .date(LocalDate.of(2023,04,30))
                .employee(employee)
                .guest(guest)
                .build();
        return appointment;
    }

}
package com.example.siouxmanagementsystem.business.impl;

import com.example.siouxmanagementsystem.domain.LicensePlate;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LicensePlateUseCaseImplTest {
    @Mock
    AppointmentRepository appointmentRepositoryMock;
    @Mock
    SendEmailUseCaseImpl sendEmailUseCase;
    @Mock
    CheckLicenseSpelling checkLicenseSpelling;
    @Mock
    SensorAvailabilityImpl sensorAvailability;
    @InjectMocks
    LicensePlateUseCaseImpl licensePlateUseCase;

    @Test
    void findMatchingLicense_ShouldReturnMatchingLicense() {
        LicensePlate licensePlate = LicensePlate.builder().licensePlate("TEST123").build();
        LocalDate date = LocalDate.of(2023, 4, 5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm");

        DepartmentEntity department = DepartmentEntity.builder()
                .id(1L)
                .name("IT")
                .build();

        EmployeeEntity employee  = EmployeeEntity.builder()
                .id(1L)
                .name("employee1")
                .department(department)
                .emailAddress("employee1@gmail.com")
                .build();

        GuestEntity guest = GuestEntity.builder()
                .id(1L)
                .name("guest1")
                .email_address("guest@gmail.com")
                .license("TES123")
                .build();


        AppointmentEntity appointment = AppointmentEntity.builder()
                .id(1L)
                .start_time(LocalTime.of(8, 00, 00).format(formatter))
                .end_time(LocalTime.of(9, 00, 00).format(formatter))
                .date(LocalDate.of(2023, 04, 5))
                .employee(employee)
                .guest(guest)
                .build();

        List<String> licenses = new ArrayList<>();
        licenses.add("TEST123");
        licenses.add("TEST456");

        when(appointmentRepositoryMock.findAllLicensePlatesByDate(date)).thenReturn(licenses);
        when(appointmentRepositoryMock.getAppointmentByLicenseAndDate(licensePlate.getLicensePlate(), date)).thenReturn(appointment);
        doNothing().when(sendEmailUseCase).createEmail(employee.getEmailAddress(), guest.getName() + " has to go to another parking");

        String findMatchingLicense = licensePlateUseCase.compareLicense(licensePlate.getLicensePlate());
        String result = "found matching license";

        assertEquals(result, findMatchingLicense);
        verify(appointmentRepositoryMock).getAppointmentByLicenseAndDate(licensePlate.getLicensePlate(),date);
    }

    @Test
    void findMatchingLicense_ShouldNotReturnMatchingLicense() {
        LicensePlate licensePlate = LicensePlate.builder().licensePlate("TEST456").build();
        LocalDate date = LocalDate.of(2023, 4, 5);

        List<String> licenses = new ArrayList<>();
        licenses.add("TEST123");
        licenses.add("TEST456");

        when(appointmentRepositoryMock.findAllLicensePlatesByDate(date)).thenReturn(licenses);
        when(appointmentRepositoryMock.getAppointmentByLicenseAndDate(licensePlate.getLicensePlate(), date)).thenReturn(null);

        String findMatchingLicense = licensePlateUseCase.compareLicense(licensePlate.getLicensePlate());
        String result = "no matching license found";

        assertEquals(result, findMatchingLicense);
        verify(appointmentRepositoryMock).getAppointmentByLicenseAndDate(licensePlate.getLicensePlate(),date);
    }
}
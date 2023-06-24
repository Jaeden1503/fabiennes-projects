package com.example.siouxmanagementsystem.business.impl.Appointment;

import com.example.siouxmanagementsystem.business.impl.Appointment.UpdateAppointmentUseCaseImpl;
import com.example.siouxmanagementsystem.domain.Appointment.UpdateAppointmentRequest;
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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateAppointmentUseCaseImplTest {

    @Mock
    private AppointmentRepository appointmentRepositoryMock;
    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private GuestRepository guestRepository;
    @InjectMocks
    private UpdateAppointmentUseCaseImpl updateAppointmentUseCase;
    @Test
    void updateAppointment() {
        UpdateAppointmentRequest request = UpdateAppointmentRequest.builder()
                .id(1L)
                .startTime(LocalTime.of(8,00,00))
                .endTime(LocalTime.of(9,00,00))
                .date(LocalDate.of(2023,04,30))
                .employeeId(1L)
                .guestId(1L)
                .guestName("guest")
                .email("guest@gmail.com")
                .licensePlate("ABC123")
                .build();

        AppointmentEntity appointment = getAppointment(request);

        when(appointmentRepositoryMock.findById(request.getId())).thenReturn(Optional.ofNullable(appointment));
        when(employeeRepository.findById(request.getEmployeeId())).thenReturn(Optional.ofNullable(appointment.getEmployee()));
        when(guestRepository.findById(request.getGuestId())).thenReturn(Optional.ofNullable(appointment.getGuest()));

        updateAppointmentUseCase.updateAppointment(request);

        verify(guestRepository,times(1)).save(appointment.getGuest());
        verify(appointmentRepositoryMock,times(1)).save(appointment);
    }

    private AppointmentEntity getAppointment(UpdateAppointmentRequest request)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm");

        EmployeeEntity employee  = EmployeeEntity.builder()
                .id(request.getEmployeeId())
                .name("test")
                .build();

        GuestEntity guest = GuestEntity.builder()
                .id(request.getGuestId())
                .name(request.getGuestName())
                .email_address(request.getEmail())
                .license(request.getLicensePlate())
                .build();


        AppointmentEntity appointment = AppointmentEntity.builder()
                .id(request.getId())
                .start_time(request.getStartTime().format(formatter))
                .end_time(request.getEndTime().format(formatter))
                .date(request.getDate())
                .employee(employee)
                .guest(guest)
                .build();
        return appointment;
    }
}
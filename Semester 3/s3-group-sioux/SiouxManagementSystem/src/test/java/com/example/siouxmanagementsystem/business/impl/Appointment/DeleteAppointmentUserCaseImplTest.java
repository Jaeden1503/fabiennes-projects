package com.example.siouxmanagementsystem.business.impl.Appointment;

import com.example.siouxmanagementsystem.business.impl.Appointment.DeleteAppointmentUserCaseImpl;
import com.example.siouxmanagementsystem.persistence.AppointmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteAppointmentUserCaseImplTest {

    @Mock
    private AppointmentRepository appointmentRepositoryMock;

    @InjectMocks
    private DeleteAppointmentUserCaseImpl deleteAppointmentUserCase;

    @Test
    void deleteAppointment() {
        Long appointmentId=1L;
        deleteAppointmentUserCase.deleteAppointment(appointmentId);
        verify(appointmentRepositoryMock,times(1)).deleteById(appointmentId);
    }
}
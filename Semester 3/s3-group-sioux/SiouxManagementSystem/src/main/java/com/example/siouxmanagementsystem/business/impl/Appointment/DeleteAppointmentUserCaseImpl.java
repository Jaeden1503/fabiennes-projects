package com.example.siouxmanagementsystem.business.impl.Appointment;

import com.example.siouxmanagementsystem.business.UseCases.AppointmentUseCases.DeleteAppointmentUseCase;
import com.example.siouxmanagementsystem.persistence.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteAppointmentUserCaseImpl implements DeleteAppointmentUseCase {
    private final AppointmentRepository appointmentRepository;

    @Override
    public void deleteAppointment(long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }
}

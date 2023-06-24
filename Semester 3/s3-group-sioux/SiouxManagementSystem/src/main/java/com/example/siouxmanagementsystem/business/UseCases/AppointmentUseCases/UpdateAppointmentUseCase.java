package com.example.siouxmanagementsystem.business.UseCases.AppointmentUseCases;

import com.example.siouxmanagementsystem.domain.Appointment.UpdateAppointmentRequest;

public interface UpdateAppointmentUseCase {
    void updateAppointment(UpdateAppointmentRequest request);
}

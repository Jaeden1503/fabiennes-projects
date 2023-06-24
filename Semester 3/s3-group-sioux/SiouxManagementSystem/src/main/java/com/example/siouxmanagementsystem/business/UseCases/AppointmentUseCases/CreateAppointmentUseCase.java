package com.example.siouxmanagementsystem.business.UseCases.AppointmentUseCases;

import com.example.siouxmanagementsystem.domain.Appointment.CreateAppointmentRequest;
import com.example.siouxmanagementsystem.domain.Appointment.CreateAppointmentResponse;

public interface CreateAppointmentUseCase {
    CreateAppointmentResponse CreateAppointment(CreateAppointmentRequest request);
}

package com.example.siouxmanagementsystem.domain.Appointment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateAppointmentResponse {
    private Long appointmentId;
}

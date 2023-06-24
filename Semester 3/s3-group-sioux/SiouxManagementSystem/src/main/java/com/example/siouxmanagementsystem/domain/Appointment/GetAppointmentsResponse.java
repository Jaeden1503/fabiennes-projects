package com.example.siouxmanagementsystem.domain.Appointment;

import com.example.siouxmanagementsystem.persistence.entity.AppointmentEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class GetAppointmentsResponse {
    private List<AppointmentEntity> appointments;
}

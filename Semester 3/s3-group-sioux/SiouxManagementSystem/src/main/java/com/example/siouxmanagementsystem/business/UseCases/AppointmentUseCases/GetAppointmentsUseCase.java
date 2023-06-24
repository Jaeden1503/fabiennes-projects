package com.example.siouxmanagementsystem.business.UseCases.AppointmentUseCases;

import com.example.siouxmanagementsystem.domain.Appointment.GetAppointmentsResponse;

import java.time.LocalDate;

public interface GetAppointmentsUseCase {
    GetAppointmentsResponse getAppointmentsByEmpId(Long empId);
    GetAppointmentsResponse getAppointmentsByEmpIdAndDate(Long empId, LocalDate date);
    GetAppointmentsResponse getAppointmentsByGuestLicense(String licensePlate);
    GetAppointmentsResponse getAppointmentsByGuestName(String name);
    GetAppointmentsResponse getAppointmentsBetweenDates(LocalDate startDate, LocalDate endDate);
}

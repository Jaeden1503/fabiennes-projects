package com.example.siouxmanagementsystem.business.impl.Appointment;

import com.example.siouxmanagementsystem.business.UseCases.AppointmentUseCases.GetAppointmentsUseCase;
import com.example.siouxmanagementsystem.domain.Appointment.GetAppointmentsResponse;
import com.example.siouxmanagementsystem.persistence.AppointmentRepository;
import com.example.siouxmanagementsystem.persistence.entity.AppointmentEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class GetAppointmentsUseCaseImpl implements GetAppointmentsUseCase {
    private final AppointmentRepository appointmentRepository;
    @Transactional
    @Override
    public GetAppointmentsResponse getAppointmentsByEmpId(Long empId) {
        List<AppointmentEntity> appointments = appointmentRepository.findAppointmentEntitiesByEmployeeId(empId)
                .stream()
                .map(AppointmentConverter::convert)
                .toList();

        GetAppointmentsResponse response = GetAppointmentsResponse.builder()
                .appointments(appointments)
                .build();
        return response;
    }

    @Override
    public GetAppointmentsResponse getAppointmentsByEmpIdAndDate(Long empId, LocalDate date) {
        List<AppointmentEntity> appointments = appointmentRepository.findAppointmentEntitiesByEmpIdAndDate(empId, date);

        return GetAppointmentsResponse.builder()
                .appointments(appointments)
                .build();
    }

    @Transactional
    @Override
    public GetAppointmentsResponse getAppointmentsByGuestLicense(String license) {
        List<AppointmentEntity> appointments = appointmentRepository.findAllByGuest_License(license)
                .stream()
                .map(AppointmentConverter::convert)
                .toList();

        GetAppointmentsResponse response = GetAppointmentsResponse.builder()
                .appointments(appointments)
                .build();
        return response;
    }

    @Transactional
    @Override
    public GetAppointmentsResponse getAppointmentsByGuestName(String name) {
        List<AppointmentEntity> appointments = appointmentRepository.findAllByGuest_Name(name)
                .stream()
                .map(AppointmentConverter::convert)
                .toList();

        GetAppointmentsResponse response = GetAppointmentsResponse.builder()
                .appointments(appointments)
                .build();
        return response;
    }

    @Transactional
    @Override
    public GetAppointmentsResponse getAppointmentsBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<AppointmentEntity> appointments = appointmentRepository.findAppointmentEntitiesByDate(startDate, endDate);

        return GetAppointmentsResponse.builder()
                .appointments(appointments)
                .build();
    }

}

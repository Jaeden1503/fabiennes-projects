package com.example.siouxmanagementsystem.business.impl.Appointment;

import com.example.siouxmanagementsystem.business.UseCases.AppointmentUseCases.CreateAppointmentUseCase;
import com.example.siouxmanagementsystem.domain.Appointment.CreateAppointmentRequest;
import com.example.siouxmanagementsystem.domain.Appointment.CreateAppointmentResponse;
import com.example.siouxmanagementsystem.persistence.AppointmentRepository;
import com.example.siouxmanagementsystem.persistence.DepartmentRepository;
import com.example.siouxmanagementsystem.persistence.EmployeeRepository;
import com.example.siouxmanagementsystem.persistence.GuestRepository;
import com.example.siouxmanagementsystem.persistence.entity.AppointmentEntity;
import com.example.siouxmanagementsystem.persistence.entity.DepartmentEntity;
import com.example.siouxmanagementsystem.persistence.entity.EmployeeEntity;
import com.example.siouxmanagementsystem.persistence.entity.GuestEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CreateAppointmentUseCaseImpl implements CreateAppointmentUseCase {
    private final EmployeeRepository employeeRepository;
    private final GuestRepository guestRepository;
    private final AppointmentRepository appointmentRepository;

    @Transactional
    @Override
    public CreateAppointmentResponse CreateAppointment(CreateAppointmentRequest request) {

        EmployeeEntity employee = employeeRepository.findEmployeeEntityById(request.getEmployeeId());

        Optional<GuestEntity> guest = guestRepository.findGuestEntityByLicense(request.getLicensePlate());
        if(guest.isEmpty())
        {
             guest = Optional.ofNullable(GuestEntity.builder()
                     .name(request.getGuestName())
                     .email_address(request.getEmail())
                     .license(request.getLicensePlate())
                     .phone_number(request.getGuestPhone())
                     .build());
             guestRepository.save(guest.get());
        }


        AppointmentEntity appointment = AppointmentEntity.builder()
                .start_time(request.getStartTime().toString())
                .end_time(request.getEndTime().toString())
                .date(request.getDate())
                .guest(guest.get())
                .employee(employee)
                .build();
        try
        {
          AppointmentEntity entity= appointmentRepository.save(appointment);
          CreateAppointmentResponse response = CreateAppointmentResponse.builder().appointmentId(entity.getId()).build();
            return response;
        }
        catch (Exception ex)
        {
            throw new RuntimeException("Error while creating appointment: " + ex.getMessage(), ex);
        }

    }
}

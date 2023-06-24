package com.example.siouxmanagementsystem.business.impl.Appointment;

import com.example.siouxmanagementsystem.business.UseCases.AppointmentUseCases.UpdateAppointmentUseCase;
import com.example.siouxmanagementsystem.domain.Appointment.UpdateAppointmentRequest;
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
public class UpdateAppointmentUseCaseImpl implements UpdateAppointmentUseCase {
    private final AppointmentRepository appointmentRepository;
    private final EmployeeRepository employeeRepository;
    private final GuestRepository guestRepository;

    @Transactional
    @Override
    public void updateAppointment(UpdateAppointmentRequest request) {
        Optional<AppointmentEntity> optionalAppointment = appointmentRepository.findById(request.getId());

        AppointmentEntity appointment = optionalAppointment.get(); // the existing appointment
        updateFields(request, appointment);
    }

    private void updateFields(UpdateAppointmentRequest request, AppointmentEntity appointment) {
        EmployeeEntity employeeEntity = employeeRepository.findById(request.getEmployeeId()).get();

        //get existing guest by id
        GuestEntity guestEntity = guestRepository.findById(request.getGuestId()).get();

        guestEntity.setId(request.getGuestId());
        guestEntity.setName(request.getGuestName());
        guestEntity.setEmail_address(request.getEmail());
        guestEntity.setLicense(request.getLicensePlate());
        guestEntity.setPhone_number(request.getGuestPhone());

        //save new data of existing guest
        guestRepository.save(guestEntity);

        appointment.setStart_time(request.getStartTime().toString());
        appointment.setEnd_time(request.getEndTime().toString());
        appointment.setDate(request.getDate());
        appointment.setEmployee(employeeEntity);
        appointment.setGuest(guestEntity);

        //update guest fields by ID
        appointmentRepository.save(appointment);
    }
}

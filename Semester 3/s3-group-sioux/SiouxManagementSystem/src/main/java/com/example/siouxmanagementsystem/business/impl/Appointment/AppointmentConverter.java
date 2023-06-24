package com.example.siouxmanagementsystem.business.impl.Appointment;

import com.example.siouxmanagementsystem.business.impl.Employee.EmployeeConverter;
import com.example.siouxmanagementsystem.business.impl.Guest.GuestConverter;
import com.example.siouxmanagementsystem.persistence.entity.AppointmentEntity;
import com.example.siouxmanagementsystem.persistence.entity.EmployeeEntity;
import com.example.siouxmanagementsystem.persistence.entity.GuestEntity;

public class AppointmentConverter {
    private AppointmentConverter(){

    }
    public static AppointmentEntity convert(AppointmentEntity appointment)
    {
        EmployeeEntity employee = EmployeeConverter.convert(appointment.getEmployee());
        GuestEntity guest = GuestConverter.convert(appointment.getGuest());
        return AppointmentEntity.builder()
                .id(appointment.getId())
                .start_time(appointment.getStart_time())
                .end_time(appointment.getEnd_time())
                .date(appointment.getDate())
                .guest(guest)
                .employee(employee)
                .build();
    }
}

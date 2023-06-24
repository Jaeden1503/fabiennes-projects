package com.example.siouxmanagementsystem.domain.Appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAppointmentRequest {
    private Long id;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    @NotNull
    private LocalDate date;

    @NotNull
    private Long employeeId;

    @NotNull
    private Long guestId;

    @NotBlank
    private String guestName;
    @NotBlank
    private String guestPhone;

    @NotBlank
    @Email
    private String email;

    @Length(max = 6)
    private String licensePlate;

}

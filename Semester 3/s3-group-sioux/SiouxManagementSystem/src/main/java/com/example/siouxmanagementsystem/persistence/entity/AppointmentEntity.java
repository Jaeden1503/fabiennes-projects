package com.example.siouxmanagementsystem.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointment")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_time")
    private String start_time;

    @Column(name = "end_time")
    private String end_time;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    private GuestEntity guest;


    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;
}

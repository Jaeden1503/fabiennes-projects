package com.example.siouxmanagementsystem.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "guest")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email_address")
    private String email_address;

    @Column(name = "license")
    private String license;

    @Column(name = "phone_number")
    private String phone_number;

    @OneToMany(mappedBy = "guest")
    @JsonIgnore
    private List<AppointmentEntity> appointments;
}

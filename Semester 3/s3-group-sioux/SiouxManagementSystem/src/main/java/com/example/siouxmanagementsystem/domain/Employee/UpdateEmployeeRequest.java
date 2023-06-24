package com.example.siouxmanagementsystem.domain.Employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeRequest {
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Long departmentId;

    @NotNull
    private String emailAddress;
}

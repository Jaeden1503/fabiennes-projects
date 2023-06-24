package com.example.siouxmanagementsystem.domain.Employee;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateEmployeeResponse {
    private Long employeeId;
}

package com.example.siouxmanagementsystem.domain.Employee;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreateEmployeesResponse {
    private List<Long> employeeIds;
}

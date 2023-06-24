package com.example.siouxmanagementsystem.domain.Employee;

import com.example.siouxmanagementsystem.persistence.entity.EmployeeEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetEmployeeResponse {
    private EmployeeEntity employee;
}

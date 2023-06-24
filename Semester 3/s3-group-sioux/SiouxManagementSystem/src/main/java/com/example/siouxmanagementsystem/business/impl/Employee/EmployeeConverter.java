package com.example.siouxmanagementsystem.business.impl.Employee;

import com.example.siouxmanagementsystem.persistence.entity.EmployeeEntity;

public class EmployeeConverter {
    public static EmployeeEntity convert(EmployeeEntity employee)
    {
        return EmployeeEntity.builder()
                .id(employee.getId())
                .emailAddress(employee.getEmailAddress())
                .name(employee.getName())
                .department(employee.getDepartment())
                .build();
    }
}

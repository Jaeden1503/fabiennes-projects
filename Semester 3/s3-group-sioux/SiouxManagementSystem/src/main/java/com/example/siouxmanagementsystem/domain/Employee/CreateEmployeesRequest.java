package com.example.siouxmanagementsystem.domain.Employee;

import com.example.siouxmanagementsystem.persistence.entity.EmployeeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeesRequest {

    @NotNull
    private List<CreateEmployeeRequest> employees;
}

package com.example.siouxmanagementsystem.controller;

import com.example.siouxmanagementsystem.business.UseCases.EmployeeUseCases.*;
import com.example.siouxmanagementsystem.domain.Employee.*;
//import com.example.siouxmanagementsystem.domain.GetEmployeesResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.example.siouxmanagementsystem.persistence.entity.EmployeeEntity;
import com.example.siouxmanagementsystem.business.UseCases.EmployeeUseCases.CreateEmployeeUseCase;
import com.example.siouxmanagementsystem.business.UseCases.EmployeeUseCases.DeleteEmployeeUseCase;
import com.example.siouxmanagementsystem.business.UseCases.EmployeeUseCases.UpdateEmployeeUseCase;
import com.example.siouxmanagementsystem.domain.Employee.CreateEmployeeRequest;
import com.example.siouxmanagementsystem.domain.Employee.CreateEmployeeResponse;
import com.example.siouxmanagementsystem.domain.Employee.UpdateEmployeeRequest;


import org.springframework.http.HttpStatus;

import javax.validation.Valid;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class EmployeesController {

    private final GetEmployeesUseCase getEmployeesUseCase;

    private final CreateEmployeeUseCase createEmployeeUseCase;

    private final CreateEmployeesUseCase createEmployeesUseCase;
    private final DeleteEmployeeUseCase deleteEmployeeUseCase;
    private final UpdateEmployeeUseCase updateEmployeeUseCase;

    @GetMapping("/department/{depName}")
    @Transactional(readOnly = true)
    public ResponseEntity<GetEmployeesResponse> getEmployeeEntitiesByDepartmentName(@PathVariable String depName)
    {
        GetEmployeesResponse response = getEmployeesUseCase.getEmployeeEntitiesByDepartmentName(depName);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{name}")
    @Transactional(readOnly = true)
    public ResponseEntity<GetEmployeesResponse> getEmployeeByName(@PathVariable String name) {
        GetEmployeesResponse response = getEmployeesUseCase.findEmployeeEntityByName(name);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<CreateEmployeeResponse> createEmployee(@RequestBody @Valid CreateEmployeeRequest request)
    {
        CreateEmployeeResponse response = createEmployeeUseCase.CreateEmployee(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/import")
    public ResponseEntity<CreateEmployeesResponse> createEmployees(@RequestBody @Valid CreateEmployeesRequest request)
    {
        CreateEmployeesResponse response = createEmployeesUseCase.CreateEmployees(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable(value = "id") final long id) {

        deleteEmployeeUseCase.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable("id") long id, @RequestBody @Valid UpdateEmployeeRequest request) {
        request.setId(id);
        updateEmployeeUseCase.updateEmployee(request);
        return ResponseEntity.noContent().build();
    }
}

package com.example.siouxmanagementsystem.controller;

import com.example.siouxmanagementsystem.business.UseCases.DepartmentUseCases.GetDepartmentsUseCase;
import com.example.siouxmanagementsystem.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class DepartmentsController {
    private final GetDepartmentsUseCase getDepartmentsUseCase;

    @GetMapping("/")
    @Transactional(readOnly = true)
    public ResponseEntity<GetDepartmentsResponse> getDepartment()
    {
        GetDepartmentsResponse response = getDepartmentsUseCase.getDepartments();
        return ResponseEntity.ok(response);
    }

}

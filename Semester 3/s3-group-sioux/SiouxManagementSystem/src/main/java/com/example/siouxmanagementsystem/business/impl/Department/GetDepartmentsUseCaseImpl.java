package com.example.siouxmanagementsystem.business.impl.Department;

import com.example.siouxmanagementsystem.business.UseCases.DepartmentUseCases.GetDepartmentsUseCase;
import com.example.siouxmanagementsystem.domain.GetDepartmentsResponse;
import com.example.siouxmanagementsystem.persistence.DepartmentRepository;
import com.example.siouxmanagementsystem.persistence.entity.DepartmentEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class GetDepartmentsUseCaseImpl implements GetDepartmentsUseCase {
    private final DepartmentRepository departmentRepository;

    @Transactional
    @Override
    public GetDepartmentsResponse getDepartments() {
        List<DepartmentEntity> departments = departmentRepository.findAll()
                .stream()
                .toList();

        GetDepartmentsResponse response = GetDepartmentsResponse.builder().departments(departments).build();

        return response;
    }
}

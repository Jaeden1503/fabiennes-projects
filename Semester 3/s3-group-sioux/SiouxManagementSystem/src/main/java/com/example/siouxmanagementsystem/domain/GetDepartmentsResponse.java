package com.example.siouxmanagementsystem.domain;

import com.example.siouxmanagementsystem.persistence.entity.DepartmentEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetDepartmentsResponse {
    private List<DepartmentEntity> departments;
}

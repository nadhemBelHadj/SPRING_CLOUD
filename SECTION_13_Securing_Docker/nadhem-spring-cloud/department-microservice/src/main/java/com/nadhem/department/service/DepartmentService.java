package com.nadhem.department.service;

import com.nadhem.department.dto.DepartmentDto;

public interface DepartmentService {
    DepartmentDto getDepartmentByCode(String code);
}
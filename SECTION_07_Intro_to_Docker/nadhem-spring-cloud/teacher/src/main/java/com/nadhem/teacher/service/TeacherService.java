package com.nadhem.teacher.service;

import com.nadhem.teacher.dto.APIResponseDto;
import com.nadhem.teacher.dto.TeacherDto;

public interface TeacherService {
    APIResponseDto getTeacherById(Long id);
}
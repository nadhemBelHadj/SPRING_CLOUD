package com.nadhem.teacher.restControllers;

import com.nadhem.teacher.dto.APIResponseDto;
import com.nadhem.teacher.dto.TeacherDto;
import com.nadhem.teacher.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/teachers")
@AllArgsConstructor
public class TeacherController {

    private TeacherService teachersService;

    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto> getTeacherById(@PathVariable("id") Long id )
    {
        return new ResponseEntity<APIResponseDto>(
                teachersService.getTeacherById(id), HttpStatus.OK);
    }
}
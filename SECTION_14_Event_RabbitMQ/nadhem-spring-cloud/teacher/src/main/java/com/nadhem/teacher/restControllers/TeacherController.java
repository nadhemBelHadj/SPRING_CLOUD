package com.nadhem.teacher.restControllers;

import com.nadhem.teacher.config.Configuration;
import com.nadhem.teacher.dto.APIResponseDto;
import com.nadhem.teacher.dto.TeacherDto;
import com.nadhem.teacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/teachers")
@AllArgsConstructor
public class TeacherController {

    private TeacherService teachersService;


    @Autowired
    Configuration configuration;

    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto> getTeacherById(@PathVariable("id") Long id )
    {
        return new ResponseEntity<APIResponseDto>(
                teachersService.getTeacherById(id), HttpStatus.OK);
    }

    @GetMapping("/author")
    public ResponseEntity<String> retrieveAuthorInfo() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(configuration.getName()+" "+configuration.getEmail() );
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTeacher(@RequestBody TeacherDto teacherDto) {
        teachersService.createTeacher(teacherDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Teacher created successfully");
    }

}
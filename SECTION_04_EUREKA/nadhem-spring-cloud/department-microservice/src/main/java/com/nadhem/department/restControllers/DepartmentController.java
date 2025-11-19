package com.nadhem.department.restControllers;

import com.nadhem.department.dto.DepartmentDto;
import com.nadhem.department.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/departments")
@AllArgsConstructor
public class DepartmentController {
    private DepartmentService departmentService;

    @GetMapping("{code}")
    public ResponseEntity<DepartmentDto> getDepByCode(@PathVariable("code") String code )
    {
        return  new ResponseEntity<DepartmentDto>(
                departmentService.getDepartmentByCode(code), HttpStatus.OK);
    }
}

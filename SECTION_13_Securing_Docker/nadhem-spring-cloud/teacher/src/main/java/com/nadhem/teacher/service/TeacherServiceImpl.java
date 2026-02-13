package com.nadhem.teacher.service;

import com.nadhem.teacher.dto.APIResponseDto;
import com.nadhem.teacher.dto.DepartmentDto;
import com.nadhem.teacher.dto.TeacherDto;
import com.nadhem.teacher.entities.Teacher;
import com.nadhem.teacher.repos.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@AllArgsConstructor
@Service
public class TeacherServiceImpl implements TeacherService {

    private TeacherRepository teacherRepository;

  //  private WebClient webClient;

    private APIClient apiClient;

    @Override
    public APIResponseDto getTeacherById(Long id) {
        String dname;
        Teacher teacher = teacherRepository.findById(id).get();

       /* DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/api/departments/" +
                        teacher.getDepCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();*/

        DepartmentDto departmentDto = apiClient.getDepByCode(teacher.getDepCode());

        if (departmentDto == null)
            dname = "Undefined";
        else
            dname=   departmentDto.getDepName();



        TeacherDto teacherDto=  new TeacherDto(
                teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getDepCode(),
                dname

        );


        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setTeacherDto(teacherDto);
        apiResponseDto.setDepartmentDto(departmentDto);

        return apiResponseDto;


    }
}

package com.nadhem.teacher.service;

import com.nadhem.teacher.dto.APIResponseDto;
import com.nadhem.teacher.dto.DepartmentDto;
import com.nadhem.teacher.dto.TeacherDto;
import com.nadhem.teacher.dto.TeacherMessageDto;
import com.nadhem.teacher.entities.Teacher;
import com.nadhem.teacher.exceptions.ResourceNotFoundException;
import com.nadhem.teacher.repos.TeacherRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@AllArgsConstructor
@Service
public class TeacherServiceImpl implements TeacherService {

    private TeacherRepository teacherRepository;

    private static final Logger log = LoggerFactory.getLogger(TeacherServiceImpl.class);
    private final StreamBridge streamBridge;


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
                teacher.getEmail(),
                teacher.getMobileNumber(),
                dname

        );


        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setTeacherDto(teacherDto);
        apiResponseDto.setDepartmentDto(departmentDto);

        return apiResponseDto;


    }

    @Override
    public void createTeacher(TeacherDto teacherDto) {

        Teacher savedTeacher = teacherRepository.save(Teacher.builder()
                .firstName(teacherDto.getFirstName())
                .lastName(teacherDto.getLastName())
                .depCode(teacherDto.getDepCode())
                .email(teacherDto.getEmail())
                .mobileNumber(teacherDto.getMobileNumber())
                .build());

        sendCommunication(savedTeacher);
    }

    @Override
    public boolean updateCommunicationStatus(Long teacherId) {
        boolean isUpdated = false;
        if(teacherId !=null ){
            Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(
                    () -> new ResourceNotFoundException("Teacher", "TeacherId", teacherId.toString())

            );
            teacher.setCommunicationDone(true);
            teacherRepository.save(teacher);
            isUpdated = true;
        }
        return  isUpdated;
    }



    private void sendCommunication(Teacher teacher) {
        var teacherMsgDto = new TeacherMessageDto(teacher.getId(), teacher.getEmail(),teacher.getMobileNumber());
        log.info("Sending Communication request for the details: {}", teacherMsgDto);
        var result = streamBridge.send("sendCommunication-out-0", teacherMsgDto);
        log.info("Is the Communication request successfully triggered ? : {}", result);
    }
}

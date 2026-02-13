package com.nadhem.teacher.functions;

import com.nadhem.teacher.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import java.util.function.Consumer;

@Configuration
public class TeacherFunctions {
    private static final Logger log = LoggerFactory.getLogger(TeacherFunctions.class);

    @Bean
    public Consumer<Long> updateCommunication(TeacherService teacherServiceService) {
        return teacherId -> {
            log.info("Updating Communication status for the teacher Id : " + teacherId.toString());
            teacherServiceService.updateCommunicationStatus(teacherId);

        };
    }
}

package com.nadhem.message.functions;

import com.nadhem.message.dto.TeacherMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunctions {

    private static final Logger log = LoggerFactory.getLogger(MessageFunctions.class);

    @Bean
    public Function<TeacherMessageDto,TeacherMessageDto> email() {
        return teacherMsgDto -> {
            log.info("Sending email with the details : " +  teacherMsgDto.toString());
            return teacherMsgDto;
        };
    }

    @Bean
    public Function<TeacherMessageDto,Long> sms() {
        return teacherMsgDto -> {
            log.info("Sending sms with the details : " +  teacherMsgDto.toString());
            return teacherMsgDto.teacherId();
        };
    }

}
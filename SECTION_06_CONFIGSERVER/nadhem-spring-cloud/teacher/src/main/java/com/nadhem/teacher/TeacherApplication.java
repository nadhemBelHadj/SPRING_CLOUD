package com.nadhem.teacher;

import com.nadhem.teacher.entities.Teacher;
import com.nadhem.teacher.repos.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@EnableFeignClients
@SpringBootApplication
public class TeacherApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeacherApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(TeacherRepository teacherRepository) {
		return args -> {
			teacherRepository.save(Teacher.builder()
					.firstName("Nadhem")
					.lastName("Bel Hadj")
					 .depCode("IT")
					.build());

		};
	}

	@Bean
	public WebClient webClient(){
		return WebClient.builder().build();
	}


}

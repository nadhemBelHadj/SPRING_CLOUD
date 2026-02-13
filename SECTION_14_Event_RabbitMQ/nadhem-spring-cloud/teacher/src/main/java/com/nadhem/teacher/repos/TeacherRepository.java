package com.nadhem.teacher.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nadhem.teacher.entities.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
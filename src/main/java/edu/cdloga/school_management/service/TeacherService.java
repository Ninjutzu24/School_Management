package edu.cdloga.school_management.service;

import edu.cdloga.school_management.model.Teacher;
import edu.cdloga.school_management.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public Set<Teacher> getAllTeachers() {
        return teacherRepository.getAllTeachers();
    }

    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }
}

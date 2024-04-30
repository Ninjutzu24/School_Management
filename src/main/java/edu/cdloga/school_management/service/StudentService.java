package edu.cdloga.school_management.service;

import edu.cdloga.school_management.model.Student;
import edu.cdloga.school_management.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Set<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }
}

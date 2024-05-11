package edu.cdloga.school_management.service;

import edu.cdloga.school_management.model.Teacher;
import edu.cdloga.school_management.model.TeacherSubject;
import edu.cdloga.school_management.model.TeacherSubjectClass;
import edu.cdloga.school_management.repository.TeacherRepository;
import edu.cdloga.school_management.repository.TeacherSubjectClassRepository;
import edu.cdloga.school_management.repository.TeacherSubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherSubjectRepository teacherSubjectRepository;
    private final TeacherSubjectClassRepository teacherSubjectClassRepository;

    public Set<Teacher> getAllTeachers() {
        return teacherRepository.getAllTeachers();
    }

    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public TeacherSubject assignTeacherToSubject(TeacherSubject teacherSubject) {
        return teacherSubjectRepository.save(teacherSubject);
    }

    public Optional<Teacher> findTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId);
    }

    public Set<TeacherSubject> getAllTeacherSubjects() {
        return teacherSubjectRepository.getAllTeacherSubjects();
    }

    public Optional<TeacherSubject> findTeacherSubjectById(Long teacherSubjectId) {
        return teacherSubjectRepository.findById(teacherSubjectId);
    }

    public TeacherSubjectClass assignTeacherToClass(TeacherSubjectClass teacherSubjectClass) {
        return teacherSubjectClassRepository.save(teacherSubjectClass);
    }
}

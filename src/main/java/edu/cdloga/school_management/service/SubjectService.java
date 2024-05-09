package edu.cdloga.school_management.service;

import edu.cdloga.school_management.model.Subject;
import edu.cdloga.school_management.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public Set<Subject> getAllSubjects() {
        return subjectRepository.getAllSubjects();
    }

    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Optional<Subject> findSubjectById(Long subjectId) {
        return subjectRepository.findById(subjectId);
    }
}

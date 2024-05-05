package edu.cdloga.school_management.service;

import edu.cdloga.school_management.model.Clazz;
import edu.cdloga.school_management.repository.ClazzRepository;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class ClazzService {

    private final ClazzRepository clazzRepository;

    public ClazzService(ClazzRepository clazzRepository) {
        this.clazzRepository = clazzRepository;
    }

    public Set<Clazz> getAllClasses() {
        return clazzRepository.getAllClasses();
    }
}

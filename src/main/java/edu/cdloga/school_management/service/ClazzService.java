package edu.cdloga.school_management.service;

import edu.cdloga.school_management.model.Clazz;
import edu.cdloga.school_management.repository.ClazzRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class ClazzService {

    private final ClazzRepository clazzRepository;

    public Set<Clazz> getAllClasses() {
        return clazzRepository.getAllClasses();
    }

    public Clazz saveClass(Clazz clazz) {
        return clazzRepository.save(clazz);
    }

}

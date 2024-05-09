package edu.cdloga.school_management.repository;

import edu.cdloga.school_management.model.Subject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface SubjectRepository extends CrudRepository<Subject, Long> {

    @Query(
            value = "select * from subjects",
            nativeQuery = true
    )
    Set<Subject> getAllSubjects();
}

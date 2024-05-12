package edu.cdloga.school_management.repository;

import edu.cdloga.school_management.model.Grade;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface GradeRepository extends CrudRepository<Grade, Long> {

    @Query(
            value = "SELECT * FROM grades ORDER BY created_at",
            nativeQuery = true
    )
    Set<Grade> getAllGrades();
}

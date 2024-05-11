package edu.cdloga.school_management.repository;

import edu.cdloga.school_management.model.TeacherSubject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TeacherSubjectRepository extends CrudRepository<TeacherSubject, Long> {

    @Query(
            value = " SELECT * FROM teachers_subjects ts",
            nativeQuery = true
    )
    Set<TeacherSubject> getAllTeacherSubjects();
}


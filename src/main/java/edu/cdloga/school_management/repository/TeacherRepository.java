package edu.cdloga.school_management.repository;

import edu.cdloga.school_management.model.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {

    @Query(
            value = "select * from teachers",
            nativeQuery = true
    )
    Set<Teacher> getAllTeachers();
}

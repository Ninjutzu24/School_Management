package edu.cdloga.school_management.repository;

import edu.cdloga.school_management.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface StudentRepository extends CrudRepository<Student, Long> {

    @Query(
            value = "select * from students;",
            nativeQuery = true
    )
    Set<Student> getAllStudents();
}

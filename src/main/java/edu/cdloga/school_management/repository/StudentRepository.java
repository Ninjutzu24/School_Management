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

    @Query(
            value = """
                    SELECT 
                        s.id,
                        s.class_id,
                        s.first_name,
                        s.middle_name,
                        s.last_name,
                        s.phone_number,
                        s.email_address,
                        s.home_address,
                        s.registration_number,
                        s.created_at,
                        s.updated_at
                    FROM students s
                        JOIN classes c ON s.class_id = c.id
                    WHERE c.id = :classId 
                    """,
            nativeQuery = true
    )
    Set<Student> getAllStudentsByClass(Long classId);
}

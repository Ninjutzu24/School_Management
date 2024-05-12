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


    @Query(
            value = """
                    SELECT 
                        ts.id,
                        ts.teacher_id,
                        ts.subject_id,
                        ts.created_at,
                        ts.updated_at
                    FROM teachers_subjects ts
                        JOIN teachers_subjects_classes tsc ON tsc.teacher_subject_id = ts.id
                        JOIN classes c ON tsc.class_id = c.id
                    WHERE c.id = :classId
                    """,
            nativeQuery = true
    )
    Set<TeacherSubject> getAllTeacherSubjectsByClass(Long classId);
}


package edu.cdloga.school_management.repository;

import edu.cdloga.school_management.model.TeacherSubjectClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherSubjectClassRepository extends CrudRepository<TeacherSubjectClass, Long> {

}


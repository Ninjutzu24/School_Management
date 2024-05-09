package edu.cdloga.school_management.repository;

import edu.cdloga.school_management.model.TeacherSubject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherSubjectRepository extends CrudRepository<TeacherSubject, Long> {

}


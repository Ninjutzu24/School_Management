package edu.cdloga.school_management.repository;

import edu.cdloga.school_management.model.Grade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface GradeRepository extends CrudRepository<Grade, Long> {

}

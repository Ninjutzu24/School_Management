package edu.cdloga.school_management.repository;

import edu.cdloga.school_management.model.Clazz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ClazzRepository extends CrudRepository<Clazz, Long> {

    @Query(
            value = "select * from classes",
            nativeQuery = true
    )
    Set<Clazz> getAllClasses();
}

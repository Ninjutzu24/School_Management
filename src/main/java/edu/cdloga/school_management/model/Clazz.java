package edu.cdloga.school_management.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(
        name = "classes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"grade", "letter"})
)
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "clazz")
    private Set<Student> students;

    @OneToMany(mappedBy = "clazz")
    private Set<TeacherSubjectClass> classesTeachersSubjects;

    @Column(nullable = false)
    private Integer grade;

    @Column(nullable = false)
    private String letter;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

}

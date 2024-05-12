package edu.cdloga.school_management.controller;

import edu.cdloga.school_management.model.Grade;
import edu.cdloga.school_management.service.GradeService;
import edu.cdloga.school_management.service.StudentService;
import edu.cdloga.school_management.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;
    private final StudentService studentService;
    private final TeacherService teacherService;

    @GetMapping(path = "/grades")
    public String getGrades(Model model) {
        model.addAttribute("grades", gradeService.getAllGrades());
        return "/grades/grades";
    }

    @GetMapping(path = "/grade/form")
    public String getGradeForm(Model model) {
        var allStudentsAssignedToAClass =
                studentService.getAllStudents()
                        .stream()
                        .filter(student -> student.getClazz() != null)
                        .collect(Collectors.toSet());
        model.addAttribute("students", allStudentsAssignedToAClass);
        model.addAttribute("teacherSubjects", teacherService.getAllTeacherSubjects());
        model.addAttribute("grade", new Grade());

        return "grades/grade_form";
    }

    @GetMapping(path = "/student/{studentId}/grade/form")
    public String getStudentGradeForm(
            @PathVariable(name = "studentId") Long studentId,
            Model model
    ) {
        var studentOptional = studentService.findStudentById(studentId);
        if (studentOptional.isEmpty()) {
            model.addAttribute("state", "notFound");
            return "grades/student_grade_form";
        }

        var student = studentOptional.get();
        var teacherTeachingAtStudentsClass =
                teacherService.getAllTeacherSubjectsByClassId(student.getClazz().getId());

        model.addAttribute("student", student);
        model.addAttribute("teacherSubjects", teacherTeachingAtStudentsClass);
        model.addAttribute("grade", new Grade());

        return "grades/student_grade_form";
    }

    @PostMapping(path = "/grade/register")
    public String registerGrade(
            @ModelAttribute(name = "grade") Grade gradeForm,
            Model model
    ) {
        if (gradeForm.getGradeValue() < 1.0f || gradeForm.getGradeValue() > 10.0f) {
            model.addAttribute("state", "notAdded");
            return "grades/grade_form";
        }

        var studentOptional = studentService.findStudentById(gradeForm.getStudent().getId());
        if (studentOptional.isEmpty()) {
            model.addAttribute("state", "notAdded");
            return "grades/grade_form";
        }

        var teacherSubjectOptional = teacherService.findTeacherSubjectById(gradeForm.getTeacherSubject().getId());
        if (teacherSubjectOptional.isEmpty()) {
            model.addAttribute("state", "notAdded");
            return "grades/grade_form";
        }

        try {
            var grade = new Grade();
            grade.setGradeValue(roundGrade(gradeForm.getGradeValue()));
            grade.setStudent(studentOptional.get());
            grade.setTeacherSubject(teacherSubjectOptional.get());

            var savedGrade = gradeService.saveGrade(grade);
            model.addAttribute("savedGrade", savedGrade);
            model.addAttribute("state", "added");
        } catch (Exception exception) {
            log.error("Exception: {}", exception.getMessage());
            model.addAttribute("state", "notAdded");
        }

        return "grades/grade_form";
    }

    @PostMapping(path = "/student/{studentId}/grade/register")
    public String registerStudentGrade(
            @PathVariable(name = "studentId") Long studentId,
            @ModelAttribute(name = "grade") Grade gradeForm,
            Model model
    ) {
        if (gradeForm.getGradeValue() < 1.0f || gradeForm.getGradeValue() > 10.0f) {
            model.addAttribute("state", "notAdded");
            return "grades/grade_form";
        }

        var studentOptional = studentService.findStudentById(studentId);
        if (studentOptional.isEmpty()) {
            model.addAttribute("state", "notAdded");
            return "grades/grade_form";
        }

        var teacherSubjectOptional = teacherService.findTeacherSubjectById(gradeForm.getTeacherSubject().getId());
        if (teacherSubjectOptional.isEmpty()) {
            model.addAttribute("state", "notAdded");
            return "grades/grade_form";
        }

        try {
            var grade = new Grade();
            grade.setGradeValue(roundGrade(gradeForm.getGradeValue()));
            grade.setStudent(studentOptional.get());
            grade.setTeacherSubject(teacherSubjectOptional.get());

            var savedGrade = gradeService.saveGrade(grade);
            model.addAttribute("savedGrade", savedGrade);
            model.addAttribute("state", "added");
        } catch (Exception exception) {
            log.error("Exception: {}", exception.getMessage());
            model.addAttribute("state", "notAdded");
        }

        return "grades/grade_form";
    }

    private Float roundGrade(Float grade) {
        return BigDecimal.valueOf(grade)
                .setScale(2, RoundingMode.UP)
                .floatValue();
    }
}

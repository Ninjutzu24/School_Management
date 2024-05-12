package edu.cdloga.school_management.controller;

import edu.cdloga.school_management.model.Clazz;
import edu.cdloga.school_management.model.Student;
import edu.cdloga.school_management.service.ClazzService;
import edu.cdloga.school_management.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Controller
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final ClazzService clazzService;

    @GetMapping(path = "/students")
    public String getStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students/students";
    }

    @GetMapping(path = "/student/form")
    public String getStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/student_form";
    }

    @PostMapping(path = "/student/register")
    public String saveStudent(Model model, @ModelAttribute("student") Student formStudent) {
        try {
            studentService.saveStudent(formStudent);
            model.addAttribute("state", "added");
        } catch (Exception exception) {
            model.addAttribute("state", "notAdded");
        }
        return "students/student_form";
    }

    @GetMapping(path = "/student/{studentId}")
    public String getStudentById(
            @PathVariable(name = "studentId") Long studentId,
            Model model
    ) {
        var studentOptional = studentService.findStudentById(studentId);
        if (studentOptional.isEmpty()) {
            model.addAttribute("state", "notFound");
            return "students/student";
        }

        var student = studentOptional.get();
        if (student.getClazz() == null) {
            model.addAttribute("class", new Clazz());
            model.addAttribute("classes", clazzService.getAllClasses());
        }
        model.addAttribute("student", student);
        return "students/student";
    }

    @PostMapping(path = "/students/{studentId}/class/assign")
    public String updateStudentsClass(
            @PathVariable(name = "studentId") Long studentId,
            @ModelAttribute(name = "class") Clazz clazzForm,
            Model model
    ) {
        var studentOptional = studentService.findStudentById(studentId);
        if (studentOptional.isEmpty()) {
            model.addAttribute("state", "studentNotFound");
            return "students/student";
        }

        var clazzOptional = clazzService.findClassById(clazzForm.getId());
        if (clazzOptional.isEmpty()) {
            model.addAttribute("state", "classNotFound");
            return "students/student";
        }

        var student = studentOptional.get();
        student.setClazz(clazzOptional.get());

        var updatedStudent = studentService.saveStudent(student);
        model.addAttribute("student", updatedStudent);
        return "students/student";
    }
}

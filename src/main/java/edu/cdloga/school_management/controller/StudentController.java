package edu.cdloga.school_management.controller;

import edu.cdloga.school_management.model.Student;
import edu.cdloga.school_management.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping(path = "/students")
    public String getStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping(path = "/student/form")
    public String getStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "student_form";
    }

    @PostMapping(path = "/student/register")
    public String saveStudent(Model model, @ModelAttribute("student") Student formStudent) {
        try {
            studentService.saveStudent(formStudent);
            model.addAttribute("state", "added");
        } catch (Exception exception) {
            model.addAttribute("state", "notAdded");
        }
        return "student_form";
    }
}

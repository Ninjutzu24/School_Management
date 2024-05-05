package edu.cdloga.school_management.controller;

import edu.cdloga.school_management.model.Teacher;
import edu.cdloga.school_management.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping(path = "/teachers")
    public String getTeachers(Model model) {
        model.addAttribute("teachers", teacherService.getAllTeachers());
        return "teachers";
    }

    @GetMapping(path = "/teacher/form")
    public String getTeacherForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacher_form";
    }

    @PostMapping(path = "/teacher/register")
    public String saveTeacher(Model model, @ModelAttribute("teacher") Teacher formTeacher) {
        try {
            teacherService.saveTeacher(formTeacher);
            model.addAttribute("state", "added");
        } catch (Exception exception) {
            model.addAttribute("state", "notAdded");
        }
        return "teacher_form";
    }
}
package edu.cdloga.school_management.controller;

import edu.cdloga.school_management.model.Subject;
import edu.cdloga.school_management.model.Teacher;
import edu.cdloga.school_management.model.TeacherSubject;
import edu.cdloga.school_management.service.ClazzService;
import edu.cdloga.school_management.service.SubjectService;
import edu.cdloga.school_management.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    private final ClazzService clazzService;
    private final SubjectService subjectService;

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

    @GetMapping("/teacher/subject/form")
    public String getTeacherSubjectForm(Model model) {
        var teacherSubject = new TeacherSubject();
        teacherSubject.setTeacher(new Teacher());
        teacherSubject.setSubject(new Subject());
        model.addAttribute("teacherSubject", teacherSubject);
        model.addAttribute("teachers",teacherService.getAllTeachers());
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "teacher_subject_form";
    }

    @PostMapping("/teacher/subject/assign")
    public String assignTeacherToSubject(
            @ModelAttribute("teacherSubject") TeacherSubject teacherSubjectForm,
            Model model
    ) {
        try {
            var subjectOptional = subjectService.findSubjectById(teacherSubjectForm.getSubject().getId());
            if(subjectOptional.isEmpty()) {
                model.addAttribute("state", "notAdded");
                return "teacher_subject_form";
            }

            var teacherOptional = teacherService.findTeacherById(teacherSubjectForm.getTeacher().getId());
            if(teacherOptional.isEmpty()) {
                model.addAttribute("state", "notAdded");
                return "teacher_subject_form";
            }

            var teacherSubject = new TeacherSubject();
            teacherSubject.setSubject(subjectOptional.get());
            teacherSubject.setTeacher(teacherOptional.get());

            var teacherSubjectSaved = teacherService.assignTeacherToClass(teacherSubject);

            model.addAttribute("state", "added");
            model.addAttribute("teacher", teacherSubjectSaved.getTeacher());
            model.addAttribute("subject", teacherSubjectSaved.getSubject());
        } catch (Exception exception) {
            log.error("Error: {}", exception.getMessage());
            model.addAttribute("state", "notAdded");
        }
        return "teacher_subject_form";
    }
}

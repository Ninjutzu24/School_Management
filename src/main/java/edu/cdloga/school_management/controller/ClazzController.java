package edu.cdloga.school_management.controller;

import edu.cdloga.school_management.model.*;
import edu.cdloga.school_management.service.ClazzService;
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
public class ClazzController {

    private final ClazzService clazzService;
    private final TeacherService teacherService;

    @GetMapping(path = "/classes")
    public String getClasses(Model model) {
        model.addAttribute("classes", clazzService.getAllClasses());
        return "classes/classes";
    }

    @GetMapping(path = "/class/form")
    public String getClazzForm(Model model) {
        model.addAttribute("clazz", new Clazz());
        return "classes/clazz_form";
    }

    @PostMapping(path = "/class/register")
    public String saveClazz(Model model, @ModelAttribute("clazz") Clazz formClazz) {
        try {
            clazzService.saveClass(formClazz);
            model.addAttribute("state", "added");
        } catch (Exception exception) {
            model.addAttribute("state", "notAdded");
        }
        return "classes/clazz_form";
    }

    @GetMapping("/class/teacher/form")
    public String getClassTeacherForm(Model model) {
        var teacherSubject = new TeacherSubject();
        teacherSubject.setTeacher(new Teacher());
        teacherSubject.setSubject(new Subject());

        var teacherSubjectClass = new TeacherSubjectClass();
        teacherSubjectClass.setClazz(new Clazz());
        teacherSubjectClass.setTeacherSubject(teacherSubject);

        model.addAttribute("teacherSubjectClass", teacherSubjectClass);
        model.addAttribute("classes", clazzService.getAllClasses());
        model.addAttribute("teacherSubjects", teacherService.getAllTeacherSubjects());

        return "classes/clazz_teacher_form";
    }

    @PostMapping("/class/teacher/assign")
    public String assignTeacherToClass(
            @ModelAttribute("teacherSubjectClass") TeacherSubjectClass teacherSubjectClassForm,
            Model model
    ) {
        try {
            var clazzOptional = clazzService.findClassById(teacherSubjectClassForm.getClazz().getId());
            if (clazzOptional.isEmpty()) {
                model.addAttribute("state", "notAdded");
                return "classes/clazz_teacher_form";
            }

            var teacherSubjectOptional =
                    teacherService.findTeacherSubjectById(teacherSubjectClassForm.getTeacherSubject().getId());
            if (teacherSubjectOptional.isEmpty()) {
                model.addAttribute("state", "notAdded");
                return "classes/clazz_teacher_form";
            }

            var teacherSubjectClass = new TeacherSubjectClass();
            teacherSubjectClass.setClazz(clazzOptional.get());
            teacherSubjectClass.setTeacherSubject(teacherSubjectOptional.get());

            var teacherSubjectClassSaved = teacherService.assignTeacherToClass(teacherSubjectClass);

            model.addAttribute("state", "added");
            model.addAttribute("teacherSubjectClass", teacherSubjectClassSaved);
        } catch (Exception exception) {
            log.error("Error: {}", exception.getMessage());
            model.addAttribute("state", "notAdded");
        }
        return "classes/clazz_teacher_form";
    }
}

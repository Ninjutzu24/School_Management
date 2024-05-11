package edu.cdloga.school_management.controller;

import edu.cdloga.school_management.model.Subject;
import edu.cdloga.school_management.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping(path = "/subjects")
    public String getSubjects(Model model) {
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "subjects/subjects";
    }

    @GetMapping(path = "/subject/form")
    public String getSubjectForm(Model model) {
        model.addAttribute("subject", new Subject());
        return "subjects/subject_form";
    }

    @PostMapping(path = "/subjects/register")
    public String saveSubject(Model model, @ModelAttribute("subject") Subject formSubject) {
        try {
            subjectService.saveSubject(formSubject);
            model.addAttribute("state", "added");
        } catch (Exception exception) {
            model.addAttribute("state", "notAdded");
        }
        return "subjects/subject_form";
    }
}

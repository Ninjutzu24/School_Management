package edu.cdloga.school_management.controller;

import edu.cdloga.school_management.model.Clazz;
import edu.cdloga.school_management.service.ClazzService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ClazzController {

    private final ClazzService clazzService;

    @GetMapping(path = "/classes")
    public String getClasses(Model model) {
        model.addAttribute("classes", clazzService.getAllClasses());
        return "classes";
    }

    @GetMapping(path = "/class/form")
    public String getClazzForm(Model model) {
        model.addAttribute("clazz", new Clazz());
        return "clazz_form";
    }

    @PostMapping(path = "/class/register")
    public String saveClazz(Model model, @ModelAttribute("clazz") Clazz formClazz) {
        try {
            clazzService.saveClass(formClazz);
            model.addAttribute("state", "added");
        } catch (Exception exception) {
            model.addAttribute("state", "notAdded");
        }
        return "clazz_form";
    }
}

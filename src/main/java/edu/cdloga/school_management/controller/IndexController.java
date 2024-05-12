package edu.cdloga.school_management.controller;

import edu.cdloga.school_management.service.ClazzService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final ClazzService clazzService;

    @GetMapping(path = "/")
    public String getIndex(Model model) {
        model.addAttribute("classes", clazzService.getAllClasses());
        return "index";
    }
}

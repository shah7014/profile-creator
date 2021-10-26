package com.learningbybuilding.resumeportal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    /*@GetMapping("/")
    public String sayHello() {
        return "Hello";
    }

    @GetMapping("/edit")
    public String edit() {
        return "edit-page";
    }*/
    @GetMapping("/view/{userId}")
    public String view(@PathVariable String userId, Model model) {
        model.addAttribute("userId", userId);
        return "resume-templates/3/index";
    }
}

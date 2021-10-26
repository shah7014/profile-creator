package com.learningbybuilding.resumeportal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String sayHello() {
        return "Hello";
    }

    @GetMapping("/edit")
    public String edit() {
        return "edit-page";
    }
}

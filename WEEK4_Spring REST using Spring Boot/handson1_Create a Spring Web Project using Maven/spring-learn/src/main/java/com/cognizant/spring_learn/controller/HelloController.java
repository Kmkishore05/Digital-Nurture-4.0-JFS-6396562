package com.cognizant.spring_learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "index"; // Will load src/main/resources/templates/index.html
    }

    @GetMapping("/welcome")
    @ResponseBody
    public String welcomeMessage() {
        return "Welcome to Spring!";
    }
}

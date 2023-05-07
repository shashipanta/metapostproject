package com.meta.userpostproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
    @GetMapping("/")
    public String loginPage(){
        return "/login";
    }

    @GetMapping("/register")
    public String registrationPage(){
        return "/login/registrationpage";
    }

}

package com.meta.userpostproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/")
    public String loginPage(){
        return "login/login-page";
    }

    @GetMapping("/register")
    public String registrationPage(){
        return "login/registration-page";
    }

}

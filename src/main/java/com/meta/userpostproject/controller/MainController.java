package com.meta.userpostproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    @GetMapping("/")
    public String loginPage(){
        return "/login/login-page";
    }

    @GetMapping("/register")
    public String registrationPage(){
        return "login/registration-page";
    }
    @GetMapping("/post_uploaded")
    public String postPage(){
        return "/post_uploaded";
    }

    @GetMapping("/uploaded_post_single_view")
    public String singlepostPage(){
        return "/uploaded_post_single_view";
    }



}

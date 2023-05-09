package com.meta.userpostproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String loginPage(){
        return "/login";
    }
//    @GetMapping("/register")
//    public String registrationPage(){
//        return "/registrationpage";
  //  }
    @GetMapping("/post_uploaded")
    public String postPage(){
        return "/post_uploaded";
    }

    @GetMapping("/create_table_view")
    public String commentPage(){
        return "/create_table_view";
    }
    @GetMapping("/uploaded_post_single_view")
    public String singlepostPage(){
        return "/uploaded_post_single_view";
    }



}

package com.meta.userpostproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
<<<<<<< HEAD

    @GetMapping("/post_uploaded")
    public String postUpload(){
        return "/post_uploaded";
    }
    @GetMapping("/create_table_view")
    public String createPost(){
        return "/create_table_view";
    }

    @GetMapping("/uploaded_post_single_view")
    public String singlePost(){
        return "/uploaded_post_single_view";
=======
    @GetMapping("/")
    public String loginPage(){
        return "/login";
    }
//    @GetMapping("/register")
//    public String registrationPage(){
//        return "/registrationpage";
//    }
    @GetMapping("/post_uploaded")
    public String postPage(){
        return "/post_uploaded";
>>>>>>> 301722bab57cd100a767b0709c5b202dd51f8408
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

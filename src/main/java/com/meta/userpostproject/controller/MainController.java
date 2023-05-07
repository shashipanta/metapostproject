package com.meta.userpostproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

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
    }


}

package com.meta.userpostproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/uploaded_post_single_view")
    public String singlePost(){
        return "/uploaded_post_single_view";
    }
    @GetMapping("/create_table_view")
    public String createPost(){
        return "/create_table_view";
    }
    @GetMapping("/post_uploaded")
    public String postUpload(){
        return "/post_uploaded";
    }


}

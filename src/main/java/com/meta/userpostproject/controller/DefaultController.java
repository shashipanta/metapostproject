package com.meta.userpostproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class DefaultController {
    @GetMapping()
    public String redirect(){
        return "/internal/postActionPage";
    }
    @GetMapping("/decidePost")
    public String adminPostDecide(){
        return "/internal/postApprovalPage";
    }
}
package com.meta.userpostproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class mainController {
    @GetMapping
    public String postApproval() {
        return "/internal/postApprovalPage";

    }
    @GetMapping("/decidePost")
    public String adminPostDecide(){
        return "internal/postApprovalPage";
    }
}

package com.meta.userpostproject.controller;

import com.meta.userpostproject.dto.PostDto;
import com.meta.userpostproject.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class DefaultController {
    private final PostService postService;

    public DefaultController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping()
    public String redirect() {
        return "/internal/postActionPage";
    }

    @GetMapping("/decidePost")
    public String adminPostDecide(Model model) {
        List<PostDto> allPost = postService.getALlPost();
        model.addAttribute("post", allPost);
        return "/internal/postApprovalPage";
    }


}

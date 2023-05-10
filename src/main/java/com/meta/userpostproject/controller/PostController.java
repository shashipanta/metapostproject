package com.meta.userpostproject.controller;

/*
The author of this class is java-suraj
lightsuraj129@gmail.com
Program was written in 2023-05-09 17:38
*/

import com.meta.userpostproject.dto.PostDto;
import com.meta.userpostproject.service.PostService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String openPostPage(Model model) {
        model.addAttribute("postDto", new PostDto());
        return "create_table_view";
    }

    @PostMapping
    public String createPost( @Valid @ModelAttribute PostDto postDto) {
        postService.createPost(postDto);
        return "redirect:/post";
    }
}

package com.meta.userpostproject.controller;

/*
The author of this class is java-suraj
lightsuraj129@gmail.com
Program was written in 2023-05-09 17:38
*/

import com.meta.userpostproject.dto.PostDto;
import com.meta.userpostproject.model.Post;
import com.meta.userpostproject.service.PostService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping()
    public String postPage(){

        return "main-page";
    }

    @GetMapping("/create")
    public String openPostPage(Model model) {
        model.addAttribute("postDto", new PostDto());
        List<Post> allPost = postService.getALlPost();
        model.addAttribute("post",allPost);
        return "post/create-post";
    }

    @PostMapping
    public String createPost( @Valid @ModelAttribute PostDto postDto) {
        postService.createPost(postDto);
        return "redirect:/post/create";
    }

    @RequestMapping ("/delete/{id}")
    public String deletePost(@PathVariable("id") short id){
        postService.deletePost(id);
        return "redirect:/post/create";
    }

    @GetMapping("/view/{id}")
    public String viewPost(@PathVariable("id") short id, Model model){
       Post editPost = postService.viewPost(id);
        System.out.println(editPost);
    model.addAttribute("post",editPost);
       return "/post/single-post-view";
    }
}

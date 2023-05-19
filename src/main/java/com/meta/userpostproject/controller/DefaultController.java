package com.meta.userpostproject.controller;

import com.meta.userpostproject.dto.PostDto;
import com.meta.userpostproject.dto.PostTableViewDto;
import com.meta.userpostproject.model.Post;
import com.meta.userpostproject.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String redirect(){
        return "/internal/postActionPage";
    }
    @GetMapping("/approve-post")
    public String adminPostDecide(Model model){


        List<PostTableViewDto> posts = postService.getAllPosts();

        model.addAttribute("posts", posts);
        return "/internal/postApprovalPage";
    }

    @GetMapping("/view-post/{id}")
    public String reviewSinglePostForApproval(@PathVariable(name = "id") Short postId, Model model){

        PostDto postDto = postService.getSinglePost(postId);

        model.addAttribute("postDto", postDto);
        return "/internal/single-post-view";
    }


}

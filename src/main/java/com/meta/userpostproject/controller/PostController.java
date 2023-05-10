package com.meta.userpostproject.controller;

/*
The author of this class is java-suraj
lightsuraj129@gmail.com
Program was written in 2023-05-09 17:38
*/

import com.meta.userpostproject.component.FileStoreUtils;
import com.meta.userpostproject.dto.PostDto;
import com.meta.userpostproject.model.Post;
import com.meta.userpostproject.service.PostService;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final FileStoreUtils fileStoreUtils;

    public PostController(PostService postService, FileStoreUtils fileStoreUtils) {
        this.postService = postService;
        this.fileStoreUtils = fileStoreUtils;
    }

    @GetMapping
    public String postPage(Model model){

        model.addAttribute("postDto", new PostDto());
        return "main-page";
    }
    @GetMapping("/create")
    public String openPost(Model model){
        model.addAttribute("categoryList", Arrays.asList("Science and Fiction", "Society", "Entertainment","Technology"));
        model.addAttribute("postDto",new PostDto());
        List<Post> allPost = postService.getALlPost();
        model.addAttribute("post",allPost);
        return "post/create-post";
    }


    @PostMapping
    public String createPost(@ModelAttribute PostDto postDto, RedirectAttributes redirectAttributes) throws TikaException, IOException {
        Boolean isValidFileType = fileStoreUtils.imageExtensionValidator(postDto.getMultipartFile());

        String redirectMessage = "";
        String errorMessage = "";
        if (isValidFileType) {
            postService.createPost(postDto);
            redirectMessage = "Post Created Successfully";
        }
        else{
            errorMessage= "Failed! File type should be jpg/png/jpeg";
        }

        redirectAttributes.addFlashAttribute("redirectMessage", redirectMessage);
        redirectAttributes.addFlashAttribute("errorMessage", errorMessage);

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
        System.out.println(editPost.getImagePath());
        model.addAttribute("post",editPost);
        return "/post/single-post-view";
    }
}

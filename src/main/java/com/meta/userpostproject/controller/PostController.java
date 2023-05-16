package com.meta.userpostproject.controller;

import com.meta.userpostproject.service.PostService;
import com.meta.userpostproject.component.FileStoreUtils;
import com.meta.userpostproject.dto.PostDto;
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

    public PostController(PostService postService,FileStoreUtils fileStoreUtils) {
        this.postService = postService;
        this.fileStoreUtils=fileStoreUtils;
    }

    @GetMapping()
    public String postPage(){
        return "external/main-page";
    }


    @GetMapping("/create")
    public String createPost(Model model){
        model.addAttribute("categoryList", Arrays.asList("Science and Fiction", "Society", "Entertainment", "Technology"));
        model.addAttribute("postDto", new PostDto());
        List<PostDto> allPost = postService.getALlPost();
        model.addAttribute("post", allPost);
        return "external/post/create-post";
    }

    @PostMapping
    public String createPost(@ModelAttribute PostDto postDto, RedirectAttributes redirectAttributes) throws TikaException, IOException {
        String type = fileStoreUtils.extensionvalidation(postDto.getMultipartFile());
        if (type.equals("image/jpeg")||type.equals("image/png")) {
            postService.createPost(postDto);
            String success_message = "Post Created Successfully";
            redirectAttributes.addFlashAttribute("success_message",success_message);
        }
        else{
            String message= "Failed! File type should be jpg or png or jpeg";
            redirectAttributes.addFlashAttribute("message",message);
        }
        return "redirect:/post/create";
    }

    @RequestMapping("/delete/{id}")
    public String deletePost ( @PathVariable("id") short id){
        postService.deletePost(id);
        return "redirect:/post/create";
    }



}

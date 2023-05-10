package com.meta.userpostproject.controller;

/*
The author of this class is java-suraj
lightsuraj129@gmail.com
Program was written in 2023-05-09 17:38
*/

import com.meta.userpostproject.component.FileStoreUtils;
import com.meta.userpostproject.dto.PostDto;
import com.meta.userpostproject.service.PostService;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@Controller
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String openPostPage(Model model) {
        model.addAttribute("categoryList", Arrays.asList("Science and Fiction", "Society", "Entertainment","Technology"));
        model.addAttribute("postDto", new PostDto());
        return "create_table_view";
    }

    @PostMapping
    public String createPost(@ModelAttribute PostDto postDto, RedirectAttributes redirectAttributes) throws TikaException, IOException {

        FileStoreUtils fileStoreUtils = new FileStoreUtils();
        String type = fileStoreUtils.extensionvalidation(postDto.getMultipartFile());
        if (type.equals("image/jpeg")) {
        postService.createPost(postDto);
        String success_message = "Post Created Successfully";
        redirectAttributes.addFlashAttribute("success_message",success_message);
        }
        else{
            String message= "Failed! File type should be jpg";
            redirectAttributes.addFlashAttribute("message",message);
        }
        return "redirect:/post";
    }
}

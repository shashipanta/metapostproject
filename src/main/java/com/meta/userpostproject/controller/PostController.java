package com.meta.userpostproject.controller;

/*
The author of this class is java-suraj
lightsuraj129@gmail.com
Program was written in 2023-05-09 17:38
*/

import com.meta.userpostproject.component.FileStoreUtils;
import com.meta.userpostproject.dto.PostDto;
import com.meta.userpostproject.dto.PostRequestDto;
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
import java.util.stream.Collectors;

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

        List<PostRequestDto> minifiedPostRequestDto = postService.getMinifiedPostList();

        model.addAttribute("minifiedPostRequestDto", minifiedPostRequestDto);

        return "main-page";
    }
    @GetMapping("/create")
    public String openPost(Model model){
        model.addAttribute("categoryList", Arrays.asList("Science and Fiction", "Society", "Entertainment","Technology"));

        if(model.getAttribute("postDto") == null) {
            model.addAttribute("postDto",new PostDto());
        }
        List<PostDto> postList = postService.getALlPost();

        // update filePath
//        List<Post> updatedPost = postList.stream()
//                .map(post -> {
//                    int index = post.getImagePath().lastIndexOf("/")+1;
//                    post.setImagePath(post.getImagePath().substring(index));
//                    return post;
//                }).collect(Collectors.toList());

        List<String> imageSrcList = postList.stream()
                                        .map(postDto -> {
                                            System.out.println(postDto.getMultipartFile().getOriginalFilename());
                                            return postDto.getMultipartFile().getOriginalFilename();
                                        })
                                        .collect(Collectors.toList());


        model.addAttribute("postList",postList);
        model.addAttribute("imageSrcList", imageSrcList);
        return "post/create-post";
    }


    @PostMapping
    public String createPost(@ModelAttribute PostDto postDto, RedirectAttributes redirectAttributes) throws TikaException, IOException {
        Boolean isValidFileType = fileStoreUtils.imageExtensionValidator(postDto.getMultipartFile());

        String redirectMessage = "";
        String errorMessage = "";
        if (isValidFileType) {
            postService.createPost(postDto);
            if(postDto.getId() != null){
                redirectMessage = "Post Updated Successfully!";
            } else {
                redirectMessage = "Post Created Successfully";
            }
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
        PostDto postDto = postService.viewPost(id);
        System.out.println(postDto.getMultipartFile().getOriginalFilename());
        model.addAttribute("postDto",postDto);
        model.addAttribute("postImageName", postDto.getMultipartFile().getOriginalFilename());
        return "/post/single-post-view";
    }

    @GetMapping("/edit/{post-id}")
    public String editPost(@PathVariable(value = "post-id") short id, RedirectAttributes redirectAttributes){
        PostDto postDto = postService.viewPost(id);

        // why postDto ? emptyform uses postDto
        redirectAttributes.addFlashAttribute("postDto", postDto);

        return "redirect:/post/create";
    }
}

package com.meta.userpostproject.controller;

import com.meta.userpostproject.dto.CategoryDto;
import com.meta.userpostproject.dto.PostTableViewDto;
import com.meta.userpostproject.service.PostService;
import com.meta.userpostproject.component.FileStoreUtils;
import com.meta.userpostproject.dto.PostDto;
import com.meta.userpostproject.service.CategoryService;
import com.meta.userpostproject.service.PostService;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    private final CategoryService categoryService;
    private final FileStoreUtils fileStoreUtils;

    public PostController(PostService postService, CategoryService categoryService, FileStoreUtils fileStoreUtils) {
        this.postService = postService;
        this.categoryService = categoryService;
        this.fileStoreUtils=fileStoreUtils;
    }

    @GetMapping()
    public String postPage(){
        return "external/main-page";
    }


    @GetMapping("/create")
    public String createPost(Model model){

        if(model.getAttribute("postDto") == null)
            model.addAttribute("postDto", new PostDto());

        List<CategoryDto> categoryDtoList = categoryService.getEnabledCategories();
//        model.addAttribute("categoryList", Arrays.asList("Science and Fiction", "Society", "Entertainment", "Technology"));
        model.addAttribute("categoryDtoList", categoryDtoList);
        model.addAttribute("postDto", new PostDto());
//        List<PostDto> allPost = postService.getALlPost();

        // table view of post
        List<PostTableViewDto> tableViewDtos = postService.getAllPosts();
        model.addAttribute("tableViewDtos", tableViewDtos);
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

    @GetMapping ("/delete/{id}")
    public String deletePost ( @PathVariable("id") short id){
        postService.deletePost(id);
        return "redirect:/post/create";
    }

    @GetMapping("/update/{id}")
    public String updatePost(@PathVariable("id") short id,RedirectAttributes redirectAttributes){
       PostDto postDto =  postService.getSinglePost(id);
       redirectAttributes.addFlashAttribute("postDto",postDto);
        return "redirect:/post/create";
    }

    @GetMapping("/view/{id}")
    public String viewPost(@PathVariable("id") short id, Model model) throws IOException {
        PostDto postDto = postService.postView(id);
        model.addAttribute("post",postDto);

        return "/external/post/single-post-view";
    }

}

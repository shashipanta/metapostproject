package com.meta.userpostproject.controller;

import com.meta.userpostproject.dto.CategoryDto;
import com.meta.userpostproject.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/category")
public class AdminController {
    private final CategoryService categoryService;

    public AdminController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
//
//    @GetMapping
//    public String admin() {
//        return"/internal/postActionPage";
//    }


    @GetMapping()
    public String adminCategory(Model model){
        List<CategoryDto> categoryDtoList = categoryService.findAll();
        model.addAttribute("category",new CategoryDto());
        model.addAttribute("categoryList",categoryDtoList);
        return "admin/Category";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("CategoryDto") CategoryDto categoryDto){
        categoryDto = categoryService.save(categoryDto);
        return "redirect:/category";
    }
}

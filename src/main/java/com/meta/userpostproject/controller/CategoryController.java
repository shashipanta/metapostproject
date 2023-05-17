package com.meta.userpostproject.controller;

import com.meta.userpostproject.dto.CategoryDto;
import com.meta.userpostproject.service.CategoryService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public String adminCategory(Model model){
        List<CategoryDto> categoryDtoList = categoryService.getCategories();
        model.addAttribute("categoryDto",new CategoryDto());
        model.addAttribute("categoryDtoList",categoryDtoList);
        return "/category/Category";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("CategoryDto") CategoryDto categoryDto){
        categoryDto = categoryService.createCategory(categoryDto);
        return "redirect:/category";
    @GetMapping(value = "/add")
    public String addCategory(CategoryDto categoryDto){

//        CategoryDto categoryDto1 = new CategoryDto(null, "Science & Technologys", "This is short description");
//        categoryService.createCategory(categoryDto1);
        return "index";
    }

    @GetMapping(value = "/{category-id}")
    public String getSingleCategory(Model model, @RequestParam(name = "category-id") Short categoryId){

        CategoryDto categoryDto = categoryService.getSingleCategory(categoryId);
        model.addAttribute("categoryDto", categoryDto);

        return "category/category";
    }

    @GetMapping()
    public String getAllCategories(Model model){
        List<CategoryDto> categoryDtoList = categoryService.getCategories();
        model.addAttribute("categoryDtoList", categoryDtoList);
//        return "fragments/category-table.html";
        return "category/category.html";
    }


    @PutMapping("/edit/{category-id}")
    public String updateCategory(CategoryDto categoryDto, @RequestParam(name = "category-id") Short categoryId){

        categoryService.updateCategory(categoryId, categoryDto);

        return "category/category";
    }
}

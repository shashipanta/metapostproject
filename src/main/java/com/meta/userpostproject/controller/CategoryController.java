package com.meta.userpostproject.Controller;

import com.meta.userpostproject.Service.CategoryService;
import com.meta.userpostproject.dto.CategoryDto;
//import com.meta.userpostproject.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    }
}

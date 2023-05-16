package com.meta.userpostproject.Controller;

import com.meta.userpostproject.Dto.CategoryDto;
import com.meta.userpostproject.Service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final CategoryService categoryService;

    public AdminController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String admin() {
        return"/internal/postActionPage";
    }


    @GetMapping("/category")
    public String adminCategory(Model model){
        List<CategoryDto> categoryDtoList = categoryService.findAll();
        model.addAttribute("category",new CategoryDto());
        model.addAttribute("categoryList",categoryDtoList);
        return "Admin/Category";
    }

    @PostMapping("/category/save")
    public String saveCategory(@ModelAttribute("CategoryDto") CategoryDto categoryDto){
        categoryDto = categoryService.save(categoryDto);
        return "redirect:/admin/category";
    }
    @GetMapping("/ca")
    public String postApproval() {
        return "/internal/postApprovalPage";

    }
    @GetMapping("/decidePost")
    public String adminPostDecide(){
        return "internal/postApprovalPage";
    }
}

package com.meta.userpostproject.controller;

import com.meta.userpostproject.dto.CategoryDto;
import com.meta.userpostproject.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

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

        if(model.containsAttribute("categoryDtoToUpdate")){
            model.addAttribute("categoryDto", model.getAttribute("categoryDtoToUpdate"));
        } else {
            model.addAttribute("categoryDto",new CategoryDto());
        }
        model.addAttribute("categoryDtoList",categoryDtoList);
        return "/category/category";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("CategoryDto") CategoryDto categoryDto,
                               RedirectAttributes redirectAttributes) {

        Short categoryId = categoryDto.getId();
        String message = "";
        try{
            categoryService.createCategory(categoryDto);

        } catch(Exception e) {
            if(e.getMessage().contains("uk_category_name")){
                message = "Category : " + categoryDto.getCategoryName() + " already exists!";
                redirectAttributes.addFlashAttribute("message", message);
            } else {
                redirectAttributes.addFlashAttribute("message", e.getLocalizedMessage());
            }

            redirectAttributes.addFlashAttribute("messageType", "error");
        }

        // either update or create

        if(categoryId == null){
            message = "Category : " + categoryDto.getCategoryName() + " created succesfully.";
            redirectAttributes.addFlashAttribute("messageType", "success");
        } else {
            message = "Category : " + categoryDto.getCategoryName() + " updated succesfully.";
        }

        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/category";
    }

    @GetMapping(value = "/{id}")
    public String getSingleCategory(Model model, @RequestParam(name = "category-id") Short categoryId){

        CategoryDto categoryDto = categoryService.getSingleCategory(categoryId);
        model.addAttribute("categoryDto", categoryDto);

        return "redirect:/category";
    }

//    @GetMapping()
//    public String getAllCategories(Model model){
//        List<CategoryDto> categoryDtoList = categoryService.getCategories();
//        model.addAttribute("categoryDtoList", categoryDtoList);
////        return "fragments/category-table.html";
//        return "category/category.html";
//    }


    @RequestMapping("/edit/{id}")
    public String updateCategory(CategoryDto categoryDto,
                                 @PathVariable(name = "id") Short categoryId,
                                 RedirectAttributes redirectAttributes){

        CategoryDto categoryDtoToUpdate = categoryService.updateCategory(categoryId, categoryDto);
        redirectAttributes.addFlashAttribute("categoryDtoToUpdate", categoryDtoToUpdate);
        return "redirect:/category";
    }

    // delete category
    @RequestMapping("/delete/{id}")
    public String deleteCategory(@PathVariable(name = "id") Short categoryId, RedirectAttributes redirectAttributes){
        Boolean isDeleted = categoryService.deleteCategory(categoryId);

        return "redirect:/category";
    }

}

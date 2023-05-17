package com.meta.userpostproject.service.serviceImpl;

import com.meta.userpostproject.Service.CategoryService;
import com.meta.userpostproject.dto.CategoryDto;
import com.meta.userpostproject.model.Category;
import com.meta.userpostproject.repo.CategoryRepo;
import com.meta.userpostproject.dto.CategoryDto;
import com.meta.userpostproject.model.Category;
import com.meta.userpostproject.repo.CategoryRepo;
import com.meta.userpostproject.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = Category
                .builder()
                .id(null)
                .categoryName(categoryDto.getCategoryName())
                .description(categoryDto.getDescription())
                .status(categoryDto.isStatus())
                .build();
        categoryRepo.save(category);
        return new CategoryDto(categoryDto.getId());
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categoryList = categoryRepo.findAll();
        return categoryList.stream().map(category -> CategoryDto.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .description(category.getDescription())
                .status(category.isStatus())
                .build()).collect(Collectors.toList());
    }
}

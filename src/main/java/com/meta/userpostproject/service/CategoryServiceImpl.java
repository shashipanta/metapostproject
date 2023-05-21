package com.meta.userpostproject.service;

import com.meta.userpostproject.dto.CategoryDto;
import com.meta.userpostproject.model.Category;
import com.meta.userpostproject.repo.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = Category
                .builder()
                .id(null)
                .categoryName(categoryDto.getCategoryName())
                .description(categoryDto.getDescription())
                .build();
        categoryRepo.save(category);
        return new CategoryDto(categoryDto.getId());
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> categoryList = categoryRepo.findAll();
        return categoryList.stream().map(category -> CategoryDto.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .description(category.getDescription())
                .build()).collect(Collectors.toList());
    }
}

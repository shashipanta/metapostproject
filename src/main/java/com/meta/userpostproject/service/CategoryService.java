package com.meta.userpostproject.service;

import com.meta.userpostproject.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);

    Boolean deleteCategory(Short categoryId);

    CategoryDto updateCategory(Short categoryId, CategoryDto categoryDto);

    List<CategoryDto> getCategories();

    CategoryDto getSingleCategory(Short categoryId);

}

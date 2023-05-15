package com.meta.userpostproject.service;

import com.meta.userpostproject.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    void deleteCategory(Short categoryId);

    CategoryDto updateCategory(Short categoryId, CategoryDto categoryDto);

    List<CategoryDto> getCategories();

    CategoryDto getSingleCategory(Short categoryId);

}

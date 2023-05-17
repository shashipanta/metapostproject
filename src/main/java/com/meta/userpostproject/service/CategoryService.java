package com.meta.userpostproject.Service;

import com.meta.userpostproject.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    List<CategoryDto> getCategories();
}

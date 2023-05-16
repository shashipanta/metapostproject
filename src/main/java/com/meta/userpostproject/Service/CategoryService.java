package com.meta.userpostproject.Service;

import com.meta.userpostproject.Dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    CategoryDto save(CategoryDto categoryDto);
    List<CategoryDto> findAll();
}

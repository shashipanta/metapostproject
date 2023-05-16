package com.meta.userpostproject.Service;

import com.meta.userpostproject.Dto.CategoryDto;
import com.meta.userpostproject.model.Category;
import com.meta.userpostproject.repo.CategoryRepo;
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
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = Category
                .builder()
                .id(null)
                .category_name(categoryDto.getCategory_name())
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
                .category_name(category.getCategory_name())
                .description(category.getDescription())
                .build()).collect(Collectors.toList());
    }
}

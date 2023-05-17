package com.meta.userpostproject.service.serviceImpl;

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
        Category category = Category.builder()
                                        .id(null)
                                        .name(categoryDto.getName())
                                        .description(categoryDto.getDescription())
                                        .build();

        category = categoryRepo.save(category);
        return new CategoryDto(category.getId());
    }

    @Override
    public void deleteCategory(Short categoryId) {

    }

    @Override
    public CategoryDto updateCategory(Short categoryId, CategoryDto categoryDto) {
        Category category = categoryBuilder(categoryDto);

        category = categoryRepo.save(category);

        return CategoryDto.builder().id(category.getId()).build();
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categoryList = categoryRepo.findAll();

        List<CategoryDto> categoryDtoList = categoryList.stream()
                                            .map(category -> categoryDtoBuilder(category))
                                            .collect(Collectors.toList());
        return categoryDtoList;
    }

    @Override
    public CategoryDto getSingleCategory(Short categoryId) {
        return null;
    }


    public CategoryDto categoryDtoBuilder(Category category){
        return CategoryDto.builder()
                            .id(category.getId())
                            .name(category.getName())
                            .description(category.getDescription())
                            .build();
    }

    public Category categoryBuilder(CategoryDto categoryDto){
        return Category.builder()
                        .id(categoryDto.getId())
                        .name(categoryDto.getName())
                        .description(categoryDto.getDescription())
                        .build();
    }
}

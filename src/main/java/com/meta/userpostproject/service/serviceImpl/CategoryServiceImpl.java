package com.meta.userpostproject.service.serviceImpl;

import com.meta.userpostproject.dto.CategoryDto;
import com.meta.userpostproject.model.Category;
import com.meta.userpostproject.model.Post;
import com.meta.userpostproject.repo.CategoryRepo;
import com.meta.userpostproject.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
                .id(categoryDto.getId())
                .name(categoryDto.getCategoryName())
                .description(categoryDto.getDescription())
                .status(categoryDto.isStatus())
                .build();
        categoryRepo.save(category);
        return new CategoryDto(categoryDto.getId());
    }

    @Override
    public Boolean deleteCategory(Short categoryId) {
        categoryRepo.deleteById(categoryId);
        if(categoryRepo.findById(categoryId).isPresent()) return Boolean.FALSE;
        return Boolean.TRUE;
    }

    @Override
    public CategoryDto updateCategory(Short categoryId, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepo.findById(categoryId);
        if(optionalCategory.isPresent()){
            Category categoryToUpdate = optionalCategory.get();

            categoryToUpdate = categoryRepo.save(categoryToUpdate);

            CategoryDto updatedCategoryDto =CategoryDto.builder()
                    .id(categoryToUpdate.getId())
                    .categoryName(categoryToUpdate.getName())
                    .description(categoryToUpdate.getDescription())
                    .status(categoryToUpdate.getStatus())
                    .build();
            return updatedCategoryDto;
        }

        return null;
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categoryList = categoryRepo.findAll();
        return categoryList.stream().map(category -> CategoryDto.builder()
                .id(category.getId())
                .categoryName(category.getName())
                .description(category.getDescription())
                .status(category.getStatus())
                .build()).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getEnabledCategories() {

        List<Category> enabledCategories = categoryRepo.getEnabledCategories();

        return enabledCategories.stream()
                        .map(
                                category -> CategoryDto.builder()
                                                        .id(category.getId())
                                                        .categoryName(category.getName())
                                                        .description(category.getDescription())
                                                        .build()
                        ).collect(Collectors.toList());



    }

    @Override
    public CategoryDto getSingleCategory(Short categoryId) {
        return null;
    }
}

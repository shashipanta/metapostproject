package com.meta.userpostproject.service;

import com.meta.userpostproject.dto.CategoryDto;
import com.meta.userpostproject.dto.RoleDto;
import com.meta.userpostproject.model.Category;
import com.meta.userpostproject.model.Role;
import com.meta.userpostproject.repo.CategoryRepo;
import com.meta.userpostproject.repo.RoleRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface CategoryService {
    CategoryDto save(CategoryDto categoryDto);
    List<CategoryDto> findAll();


}

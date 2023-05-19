package com.meta.userpostproject.repo;

import com.meta.userpostproject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Short> {

    @Query("SELECT c FROM Category c WHERE c.status = true")
    List<Category> getEnabledCategories();
}

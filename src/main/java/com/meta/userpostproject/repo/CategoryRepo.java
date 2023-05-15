package com.meta.userpostproject.repo;

import com.meta.userpostproject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Short> {

}

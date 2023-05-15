package com.meta.userpostproject.repo;

import com.meta.userpostproject.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Post,Short> {
}

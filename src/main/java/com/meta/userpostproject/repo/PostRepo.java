package com.meta.userpostproject.repo;

import com.meta.userpostproject.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post,Short> {
}

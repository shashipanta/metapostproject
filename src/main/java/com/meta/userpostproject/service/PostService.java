package com.meta.userpostproject.service;

import com.meta.userpostproject.dto.PostDto;
import com.meta.userpostproject.model.Post;
import org.apache.tika.exception.TikaException;

import java.io.IOException;
import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto) throws IOException, TikaException;

    List<Post> getALlPost();

    void deletePost(short id);

    Post viewPost(short id);
}

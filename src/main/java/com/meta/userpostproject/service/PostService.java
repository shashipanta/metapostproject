package com.meta.userpostproject.service;

import com.meta.userpostproject.dto.PostDto;
import org.apache.tika.exception.TikaException;

import java.io.IOException;

public interface PostService {

    PostDto createPost(PostDto postDto) throws TikaException, IOException;
}

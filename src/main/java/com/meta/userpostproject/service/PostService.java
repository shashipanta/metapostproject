package com.meta.userpostproject.Service;

import com.meta.userpostproject.dto.PostDto;
import org.apache.tika.exception.TikaException;

import java.io.IOException;
import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto) throws  TikaException, IOException;

    List<PostDto> getALlPost();

    void deletePost(short id);

    PostDto getSinglePost(short id);




}

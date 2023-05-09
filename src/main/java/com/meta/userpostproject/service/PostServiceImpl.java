package com.meta.userpostproject.service;

import com.meta.userpostproject.component.FileStoreUtils;
import com.meta.userpostproject.dto.PostDto;
import com.meta.userpostproject.model.Post;
import com.meta.userpostproject.repo.PostRepo;
import org.springframework.stereotype.Service;

/*
The author of this class is java-suraj
lightsuraj129@gmail.com
Program was written in 2023-05-09 17:48
*/
@Service
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;
    private final FileStoreUtils fileStoreUtils;

    public PostServiceImpl(PostRepo postRepo, FileStoreUtils fileStoreUtils) {
        this.postRepo = postRepo;
        this.fileStoreUtils = fileStoreUtils;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post =
                Post.builder()
                        .id(null)
                        .title(postDto.getTitle())
                        .description(postDto.getDescription())
                        .imagePath(fileStoreUtils.saveMultipartFile(postDto.getMultipartFile()))
                        .build();
        post = postRepo.save(post);
        return new PostDto(post.getId());
    }
}

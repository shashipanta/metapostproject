package com.meta.userpostproject.service;

import com.meta.userpostproject.component.FileStoreUtils;
import com.meta.userpostproject.dto.PostDto;
import com.meta.userpostproject.model.Post;
import com.meta.userpostproject.repo.PostRepo;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


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
    public PostDto createPost(PostDto postDto) throws IOException {
        Post post =
                Post.builder()
                        .id(null)
                        .title(postDto.getTitle())
                        .category(postDto.getCategory())
                        .description(postDto.getDescription())
                        .imagePath(fileStoreUtils.saveMultipartFile(postDto.getMultipartFile()))
                        .build();
        post = postRepo.save(post);
        return new PostDto(post.getId());
    }


    @Override
    public List<Post> getALlPost() {
        List<Post> allPost = postRepo.findAll();
        return allPost;
    }

    @Override
    public void deletePost(short id) {
        postRepo.deleteById(id);
    }

    @Override
    public Post viewPost(short id) {
        Post post = postRepo.findById(id).get();
        return post;
    }
}

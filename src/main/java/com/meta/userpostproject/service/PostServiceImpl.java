package com.meta.userpostproject.service;

import com.meta.userpostproject.component.FileStoreUtils;
import com.meta.userpostproject.component.PostMultipartFile;
import com.meta.userpostproject.dto.PostDto;
import com.meta.userpostproject.model.Post;
import com.meta.userpostproject.repo.PostRepo;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


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
                        .id(postDto.getId())
                        .title(postDto.getTitle())
                        .category(postDto.getCategory())
                        .description(postDto.getDescription())
                        .imagePath(fileStoreUtils.saveMultipartFile(postDto.getMultipartFile()))
                        .build();
        post = postRepo.save(post);
        return new PostDto(post.getId());
    }


    @Override
    public List<PostDto> getALlPost() {
        List<Post> postList = postRepo.findAll();
        return postList.stream()
                .map(post -> {
                    int index = post.getImagePath().lastIndexOf("/") + 1;
                    String fileName = post.getImagePath().substring(index);
                    return PostDto.builder()
                            .id(post.getId())
                            .title(post.getTitle())
                            .category(post.getCategory())
                            .description(post.getDescription())
                            .multipartFile(new PostMultipartFile(fileName))
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deletePost(short id) {
        postRepo.deleteById(id);
    }

    @Override
    public PostDto viewPost(short id) {
        Post post = postRepo.findById(id).orElseThrow();
        int index = post.getImagePath().lastIndexOf("/") + 1;
        String fileName = post.getImagePath().substring(index);
        return PostDto.builder()
                .id(post.getId())
                .category(post.getCategory())
                .title(post.getTitle())
                .description(post.getDescription())
                .multipartFile(new PostMultipartFile(fileName))
                .build();
    }
}

package com.meta.userpostproject.service.serviceImpl;

import com.meta.userpostproject.component.FileStoreUtils;
import com.meta.userpostproject.dto.PostDto;
import com.meta.userpostproject.model.Post;
import com.meta.userpostproject.repo.PostRepo;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements com.meta.userpostproject.service.PostService {
    private final PostRepo postRepo;
    private final FileStoreUtils fileStoreUtils;

    public PostServiceImpl(PostRepo postRepo, FileStoreUtils fileStoreUtils) {
        this.postRepo = postRepo;
        this.fileStoreUtils = fileStoreUtils;
    }

    //create post
    @Override
    public PostDto createPost(PostDto postDto) throws  TikaException, IOException {
        Post post =
                Post.builder()
                        .id(null)
                        .title(postDto.getTitle())
                        .description(postDto.getDescription())
                        .category(postDto.getCategory())
                        .imagePath(fileStoreUtils.saveMultipartFile(postDto.getMultipartFile()))
                        .build();
        post = postRepo.save(post);
        return new PostDto(post.getId());
    }


    //view all post in table
    @Override
    public List<PostDto> getALlPost() {
        List<Post> allPost = postRepo.findAll();

        return allPost.stream().map(post -> PostDto.builder()
                        .id(post.getId())
                        .description(post.getDescription())
                        .title(post.getTitle())
                        .category(post.getCategory())
                        .filePath(post.getImagePath()).build())
                .collect(Collectors.toList());
    }

    //delete post
    @Override
    public void deletePost(short id) {
        postRepo.deleteById(id);
    }

    //get single post
    @Override
    public PostDto getSinglePost(short id) {
       Optional<Post> post =  postRepo.findById(id);
       if(post.isPresent()){
           Post post1 = post.get();
           return PostDto.builder()
                   .id(post1.getId())
                   .title(post1.getTitle())
                   .category(post1.getCategory())
                   .description(post1.getDescription()).build();
       }else {
           return null;
       }
    }


}

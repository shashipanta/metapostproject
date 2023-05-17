package com.meta.userpostproject.service.serviceImpl;

import com.meta.userpostproject.component.FileStoreUtils;
import com.meta.userpostproject.dto.CategoryDto;
import com.meta.userpostproject.dto.PostDto;
import com.meta.userpostproject.model.Category;
import com.meta.userpostproject.model.Post;
import com.meta.userpostproject.repo.CategoryRepo;
import com.meta.userpostproject.repo.PostRepo;
import com.meta.userpostproject.service.PostService;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepo postRepo;
    private final CategoryRepo categoryRepo;
    private final FileStoreUtils fileStoreUtils;

    public PostServiceImpl(PostRepo postRepo, CategoryRepo categoryRepo, FileStoreUtils fileStoreUtils) {
        this.postRepo = postRepo;
        this.categoryRepo = categoryRepo;
        this.fileStoreUtils = fileStoreUtils;
    }

    //create post
    @Override
    public PostDto createPost(PostDto postDto) throws  TikaException, IOException {
//        Category selectedCategory = Category.builder()
//                                            .name(postDto.getCategoryDto().getName())
//                                            .id(postDto.getCategoryDto().getId())
//                                            .description(postDto.getCategoryDto().getDescription())
//                                            .build();
        Category selectedCategory = categoryRepo.findById(postDto.getCategoryId()).get();

        Post post =
                Post.builder()
                        .id(null)
                        .title(postDto.getTitle())
                        .description(postDto.getDescription())
                        .category(selectedCategory)
                        .imagePath(fileStoreUtils.saveMultipartFile(postDto.getMultipartFile()))
                        .build();
        post = postRepo.save(post);
        return new PostDto(post.getId());
    }


    //view all post in table
    @Override
    public List<PostDto> getALlPost() {
        List<Post> allPost = postRepo.findAll();

        return allPost.stream()
                        .map(post -> PostDto.builder()
                            .id(post.getId())
                            .description(post.getDescription())
                            .title(post.getTitle())
                            .categoryId(post.getCategory().getId())
                            .filePath(post.getImagePath()).build()
                        )
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
           Post foundPost = post.get();
           return PostDto.builder()
                   .id(foundPost.getId())
                   .title(foundPost.getTitle())
                   .categoryId(foundPost.getCategory().getId())
                   .description(foundPost.getDescription()).build();
       }else {
           return null;
       }
    }


}

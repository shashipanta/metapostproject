package com.meta.userpostproject.service;

import com.meta.userpostproject.component.FileStoreUtils;
import com.meta.userpostproject.component.PostMultipartFile;
import com.meta.userpostproject.dto.PostDto;
import com.meta.userpostproject.dto.PostRequestDto;
import com.meta.userpostproject.model.Post;
import com.meta.userpostproject.repo.PostRepo;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
                        .imagePath(fileStoreUtils.saveMultipartFile(postDto.getMultipartFile(), postDto))
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
        Optional<Post> post = postRepo.findById(id);

        if(post.isPresent()){
            String filePath = post.get().getImagePath();
            int index = filePath.lastIndexOf(File.separator);
            String fileName = filePath.substring(index + 1);

            // delete the file
            fileStoreUtils.deleteSavedImageFile(fileName);

        }
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

    // minified List of postRequestDto for main page
    public List<PostRequestDto> getMinifiedPostList() {
        List<Post> postList = postRepo.findAll();

        List<PostRequestDto> minifiedPostRequestDto = postList.stream()
                .map(post -> {
                    PostRequestDto postRequestDto = PostRequestDto
                            .builder()
                            .id(post.getId())
                            .title(post.getTitle())
                            .imageName(extractFileName(post))
                            .shortDescription(post.getDescription())
                            .publishedDate(LocalDateTime.now())
                            .username("userName")
                            .build();
                    postRequestDto.setShortDescription(postRequestDto.getMinifiedDescription());
                    return postRequestDto;
                }).collect(Collectors.toList());
        return minifiedPostRequestDto;
    }

    // extract imageName from filePath
    public String extractFileName(Post post){
        int index = post.getImagePath().lastIndexOf("/") + 1;
        String fileName = post.getImagePath().substring(index);
        return fileName;
    }
}

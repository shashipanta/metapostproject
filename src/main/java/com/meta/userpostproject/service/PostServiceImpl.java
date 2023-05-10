package com.meta.userpostproject.service;

import com.meta.userpostproject.component.FileStoreUtils;
import com.meta.userpostproject.dto.PostDto;
import com.meta.userpostproject.model.Post;
import com.meta.userpostproject.repo.PostRepo;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

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
    public PostDto createPost(PostDto postDto) throws TikaException, IOException {
//        Tika tika = new Tika();
//        String type = tika.detect((InputStream) postDto.getMultipartFile());
//        if (type.equals("image/jpg")) {
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
//        }else {
//            return null;
//
//
//            }
        }
    }

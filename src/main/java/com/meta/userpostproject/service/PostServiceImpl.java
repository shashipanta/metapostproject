//package com.meta.userpostproject.service;
//
//import com.meta.userpostproject.component.FileStoreUtils;
//import com.meta.userpostproject.dto.PostDto;
//import com.meta.userpostproject.dto.PostTableViewDto;
//import com.meta.userpostproject.model.Post;
//import com.meta.userpostproject.repo.PostRepo;
//import org.apache.tika.exception.TikaException;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class PostServiceImpl implements com.meta.userpostproject.service.PostService {
//
//
//    private final PostRepo postRepo;
//    private final FileStoreUtils fileStoreUtils;
//
//    public PostServiceImpl(PostRepo postRepo, FileStoreUtils fileStoreUtils) {
//        this.postRepo = postRepo;
//        this.fileStoreUtils = fileStoreUtils;
//    }
//
//    //create post
//    @Override
//    public PostDto createPost(PostDto postDto) throws  TikaException, IOException {
//
//        LocalDateTime localDateTime = LocalDateTime.now();
//        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/YYYY -- E H:m a");
//        String myDate = localDateTime.format(df);
//        System.out.println(myDate);
//
//        Post post =
//                Post.builder()
//                        .id(postDto.getId())
//                        .title(postDto.getTitle())
//                        .description(postDto.getDescription())
//                        .category(postDto.getCategory())
//                        .dateTime(myDate)
//                        .imagePath(fileStoreUtils.saveMultipartFile(postDto.getMultipartFile()))
//                        .build();
//        post = postRepo.save(post);
//        return new PostDto(post.getId());
//    }
//
//
//    //view all post in table
//    @Override
//    public List<PostDto> getALlPost() {
//        List<Post> allPost = postRepo.findAll();
//
//        return allPost.stream().map(post -> PostDto.builder()
//                        .id(post.getId())
//                        .description(post.getDescription())
//                        .title(post.getTitle())
//                        .dateTime(post.getDateTime())
//                        .category(post.getCategory()).build())
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<PostTableViewDto> getAllPosts() {
//        return null;
//    }
//
//    //delete post
//    @Override
//    public void deletePost(short id) {
//        postRepo.deleteById(id);
//    }
//
//    //get single post
//    @Override
//    public PostDto getSinglePost(short id) {
//       Optional<Post> singlePost =  postRepo.findById(id);
//       if(singlePost.isPresent()){
//           Post post = singlePost.get();
//           return PostDto.builder()
//                   .id(post.getId())
//                   .title(post.getTitle())
//                   .category(post.getCategory())
//                   .dateTime(post.getDateTime())
//                   .description(post.getDescription()).build();
//       }else {
//           return null;
//       }
//    }
//
//    //view post
//    @Override
//    public PostDto postView(short id) throws IOException {
//        Optional<Post> post =  postRepo.findById(id);
//        if(post.isPresent()){
//            Post viewPost = post.get();
//            return PostDto.builder()
//                    .id(viewPost.getId())
//                    .title(viewPost.getTitle())
//                    .category(viewPost.getCategory())
//                    .description(viewPost.getDescription())
//                    .filePath(fileStoreUtils.getBase64FormFilePath(viewPost.getImagePath()))
//                    .dateTime(viewPost.getDateTime())
//                    .build();
//        }else {
//            return null;
//        }
//    }
//
//}

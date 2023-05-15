package com.meta.userpostproject.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostDto {
    private Short id;

    private String title;

    private String description;

    private String category;


    private MultipartFile multipartFile;

    private String filePath;

    public PostDto(Short id) {
        this.id = id;
    }
}

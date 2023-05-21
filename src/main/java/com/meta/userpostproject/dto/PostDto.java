package com.meta.userpostproject.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;


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

    private String dateTime;

    private MultipartFile multipartFile;

    private String filePath;

    private Short categoryId;

    public PostDto(Short id) {
        this.id = id;
    }

}


package com.meta.userpostproject.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/*
The author of this class is java-suraj
lightsuraj129@gmail.com
Program was written in 2023-05-09 17:40
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Short id;

    private String title;
    private String category;

    private String description;

    private MultipartFile multipartFile;

    public PostDto(Short id) {
        this.id = id;
    }
}

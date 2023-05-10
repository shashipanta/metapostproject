package com.meta.userpostproject.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

/*
The author of this class is java-suraj
lightsuraj129@gmail.com
Program was written in 2023-05-09 17:40
*/
@Data
@Builder
@AllArgsConstructor

public class PostDto {


    private Short id;

    private String title;

    @NotEmpty(message = "description must be provided.")
    private String description;

    @NonNull
    private MultipartFile multipartFile;

    public PostDto(Short id) {
        this.id = id;
    }

    public PostDto() {

    }
}

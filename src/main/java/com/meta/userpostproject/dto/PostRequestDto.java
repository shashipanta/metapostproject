package com.meta.userpostproject.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PostRequestDto {

    private static final int MAX_CHAR_LENGTH = 100;

    private String title;
    private Short id;
    private String imageName;
    private String shortDescription;
    private String username;
    private LocalDateTime publishedDate;


    public String getMinifiedDescription(){
        if(shortDescription.length()> MAX_CHAR_LENGTH) {
            return shortDescription.substring(0, MAX_CHAR_LENGTH);
        }

        return shortDescription;
    }
}

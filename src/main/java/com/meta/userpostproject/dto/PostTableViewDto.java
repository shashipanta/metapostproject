package com.meta.userpostproject.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostTableViewDto {
    private static final int MAX_CHARACTERS = 40;

    private Short id;
    private String title;
    private String category;
    private String description;

    // get minified post
    public static String minifyPostDescription(String description){

        if(description.length() > MAX_CHARACTERS){
            description = description.substring(0, MAX_CHARACTERS-3) + "...";
        }
        return description;
    }
}

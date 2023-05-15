package com.meta.userpostproject.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Builder
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CategoryDto {

    private Short id;

    private String name;

    private String description;

    public CategoryDto(Short id){
        this.id = id;
    }


}

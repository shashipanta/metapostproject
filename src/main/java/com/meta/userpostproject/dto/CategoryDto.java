package com.meta.userpostproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    private short id;
    private String categoryName;
    private String description;
    private boolean status;

    public CategoryDto(short id) {
        this.id = id;
    }
}

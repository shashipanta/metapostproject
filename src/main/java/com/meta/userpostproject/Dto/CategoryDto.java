package com.meta.userpostproject.Dto;

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
    private String category_name;
    private String description;
    private boolean status;

    public CategoryDto(short id) {
        this.id = id;
    }
}

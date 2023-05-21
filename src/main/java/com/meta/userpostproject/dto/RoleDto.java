package com.meta.userpostproject.dto;

import com.meta.userpostproject.model.Role;
import com.meta.userpostproject.model.RoleType;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDto {
    private Short id;
    private String name;
    private String description;
    private RoleType roleType;

    public RoleDto(Role role){
        this.id=role.getId();
        this.name= role.getName();
        this.description= role.getDescription();
        this.roleType=role.getRoleType();
    }
    public RoleDto(Short id) {
    }
}

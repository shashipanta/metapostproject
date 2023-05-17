package com.meta.userpostproject.model;

/*
The author of this class is java-suraj
lightsuraj129@gmail.com
Program was written in 2023-05-02 18:04
*/

import com.meta.userpostproject.dto.RoleDto;
import com.meta.userpostproject.enums.RoleType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@Table(name = "role", uniqueConstraints = {
        @UniqueConstraint(name = "uk_role_name", columnNames = "name")
})
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    @Column(name = "name")
    private String name;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type")
    private RoleType roleType;

    public Role(RoleDto roleDto){
        this.id=roleDto.getId();
        this.name= roleDto.getName();
        this.description= roleDto.getDescription();
        this.roleType=roleDto.getRoleType();
    }

}

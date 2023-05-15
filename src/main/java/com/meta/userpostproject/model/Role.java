package com.meta.userpostproject.model;

/*
The author of this class is java-suraj
lightsuraj129@gmail.com
Program was written in 2023-05-02 18:04
*/

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

}

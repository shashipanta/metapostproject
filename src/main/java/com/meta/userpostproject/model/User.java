package com.meta.userpostproject.model;

import com.meta.userpostproject.enums.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/*
The author of this class is java-suraj
lightsuraj129@gmail.com
Program was written in 2023-05-02 18:10
*/
@Entity
@Builder
@Getter
@Setter
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "uk_users_username", columnNames = "username"),
        @UniqueConstraint(name = "uk_users_email", columnNames = "email"),
        @UniqueConstraint(name = "uk_users_mobilenumber", columnNames = "mobile_number")
})
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "username", length = 20, nullable = false)
    private String username;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "mobile_number", length = 10, nullable = false)
    private String mobileNumber;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false, length = 10)
    private UserType userType;

    @ManyToMany(targetEntity = Role.class)
    @JoinTable(name = "user_role_generated_table")
    private List<Role> roleList;


    @OneToMany(targetEntity = Post.class,mappedBy = "user")
    private List<Post> postList;
}

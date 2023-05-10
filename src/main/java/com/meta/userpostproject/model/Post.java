package com.meta.userpostproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


/*
The author of this class is java-suraj
lightsuraj129@gmail.com
Program was written in 2023-05-02 18:18
*/
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "post", uniqueConstraints = {
})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(nullable = false, length = 100)
    private String title;

    private String description;

    @Column(name = "category", nullable = false, length = 30)
    private String category;

    @Column(name = "image_path", length = 200, nullable = false)
    private String imagePath;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_post_userid"))
    private User user;
}

package com.meta.userpostproject.model;

import jakarta.persistence.*;
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
@Table(name = "post", uniqueConstraints = {
})
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "category", nullable = false, length = 100)
    private String category;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_path", length = 200, nullable = false)
    private String imagePath;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_post_userid"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Category.class)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_post_categoryid"))
    private Category categorys;
}

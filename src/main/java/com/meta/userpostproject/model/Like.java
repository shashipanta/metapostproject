package com.meta.userpostproject.model;

import jakarta.persistence.*;
import lombok.*;

/*
The author of this class is java-suraj
lightsuraj129@gmail.com
Program was written in 2023-05-02 18:24
*/
@Entity
@Builder
@Getter
@Setter
@Table(name = "like_table", uniqueConstraints = {
})
@NoArgsConstructor
@AllArgsConstructor
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(name = "fk_like_post"))
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_like_user"))
    private User user;
}

package com.bharath.learning.socialmediablog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name = "comments")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Size(min = 3, message= "User Name should have at least 3 Characters")
    private String userName;
    @NotEmpty
    @NotNull
    @Email
    private String email;
    @NotEmpty
    @NotNull
    @Size(min = 7, message= "Comment body should have at least 7 Characters")
    private String body;

    // ManyToOne Relation
    // Multiple comments can belong to single posts
    //Comments table is managing the relationship b/w Posts and Comments Table

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity postEntity;

}

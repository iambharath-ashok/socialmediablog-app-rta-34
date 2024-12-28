package com.bharath.learning.socialmediablog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 3, message= "Post Title should have at least 3 Characters")
    @Column(name = "title")
    private String title;

    @NotEmpty
    @Size(min = 5, message= "Post description should have at least 5 Characters")
    @Column(name = "description")
    private String description;

    @NotEmpty
    @Size(min = 7, message= "Post content should have at least 7 Characters")
    @Column(name = "content")
    private String content;

    //OneToMany Relationship
    //Single posts can have multiple comments
    @OneToMany(mappedBy = "postEntity")
    private Set<CommentEntity> comments = new HashSet<>();


}

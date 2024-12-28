package com.bharath.learning.socialmediablog.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {

    private Long id;
    @NotEmpty
    @Size(min = 3, message= "Post Title should have at least 3 Characters")
    private String title;
    @NotEmpty
    @Size(min = 5, message= "Post description should have at least 5 Characters")
    private String description;
    @NotEmpty
    @Size(min = 7, message= "Post content should have at least 7 Characters")
    private String content;
}

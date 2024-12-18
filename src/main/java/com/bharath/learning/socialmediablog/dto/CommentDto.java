package com.bharath.learning.socialmediablog.dto;

import lombok.Data;

@Data
public class CommentDto {

    private long id;
    private String userName;
    private String email;
    private String body;

}

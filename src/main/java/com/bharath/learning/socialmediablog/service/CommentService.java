package com.bharath.learning.socialmediablog.service;

import com.bharath.learning.socialmediablog.dto.CommentDto;
import com.bharath.learning.socialmediablog.entity.CommentEntity;

import java.util.List;

public interface CommentService {

    List<CommentDto> getAllCommentsByPostId(long postId);

    CommentDto getCommentByPostIdAndCommentId(long postId, long commentId);

    CommentDto createCommentForPost(long postId, CommentDto commentDto);

    //update
    CommentDto updateCommentByPostIdAndCommentId(long postId, long commentId, CommentDto commentDto);


    //delete

    //Delete Particular comment on particular post
    String deleteCommentByPostIdAndCommentId(long postId, long commentId);

    // Delete all comments on particular post

    String deleteAllCommentsOfPostsFromPostId(long postId);


}

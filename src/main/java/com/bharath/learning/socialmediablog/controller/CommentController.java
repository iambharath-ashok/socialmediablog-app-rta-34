package com.bharath.learning.socialmediablog.controller;

import com.bharath.learning.socialmediablog.dto.CommentDto;
import com.bharath.learning.socialmediablog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class CommentController {

    @Autowired
    private CommentService commentService;



    // Create Comment
    // /posts/{post-id}/comments

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable long postId, @RequestBody CommentDto commentDto) {
      CommentDto savedCommentDto =  this.commentService.createCommentForPost(postId, commentDto);
      return new ResponseEntity<>(savedCommentDto, HttpStatus.CREATED);
    }

    // Get All Comments for Post REST API - /posts/{post-id}/comments

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> fetchAllCommentsByPostId(@PathVariable long postId) {
        List<CommentDto> commentDtoList = this.commentService.getAllCommentsByPostId(postId);
        return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
    }



    // GET Single Comment REST API - /posts/{post-id}/comments/{comment-id}
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> fetchCommentByPostIdAndCommentId(@PathVariable long postId, @PathVariable long commentId) {
        CommentDto commentDto =  this.commentService.getCommentByPostIdAndCommentId(postId, commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    // Update Mapping
    // Update Comment REST API - /posts/{post-id}/comments/{comment-id}
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateCommentByPostIdAndCommentId(@PathVariable long postId, @PathVariable long commentId, @RequestBody CommentDto commentDto) {
        CommentDto updatedCommentDto =  this.commentService.updateCommentByPostIdAndCommentId(postId, commentId, commentDto);
        return new ResponseEntity<>(updatedCommentDto, HttpStatus.OK);
    }

    //Delete Comment REST API - /posts/{post-id}/comments/{comment-id}
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentByPostIdAndCommentId(@PathVariable long postId, @PathVariable long commentId) {
        String message =  this.commentService.deleteCommentByPostIdAndCommentId(postId, commentId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //Delete Comment REST API - /posts/{post-id}/comments
    @DeleteMapping("/posts/{postId}/comments")
    public ResponseEntity<String> deleteAllCommentsByPostId(@PathVariable long postId) {
        String message =  this.commentService.deleteAllCommentsOfPostsFromPostId(postId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}

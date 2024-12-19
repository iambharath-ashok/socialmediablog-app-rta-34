package com.bharath.learning.socialmediablog.controller;

import com.bharath.learning.socialmediablog.dto.CommentDto;
import com.bharath.learning.socialmediablog.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
@Tag(name = "Comments API", description = "API for managing comments on posts. Allows users to create, fetch, update, and delete comments for posts.")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // Create Comment
    // /posts/{post-id}/comments
    @Operation(summary = "Create a new comment", description = "Create a new comment for a specific post by providing the post ID and comment details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the comment"),
            @ApiResponse(responseCode = "400", description = "Invalid comment data")
    })
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @Parameter(description = "ID of the post to add the comment to", required = true) @PathVariable long postId,
            @Parameter(description = "Comment details to be added", required = true) @RequestBody CommentDto commentDto) {
        CommentDto savedCommentDto = this.commentService.createCommentForPost(postId, commentDto);
        return new ResponseEntity<>(savedCommentDto, HttpStatus.CREATED);
    }

    // Get All Comments for Post REST API - /posts/{post-id}/comments
    @Operation(summary = "Fetch all comments for a post", description = "Retrieve a list of all comments for a specific post by its post ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched all comments for the post"),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> fetchAllCommentsByPostId(@Parameter(description = "ID of the post to fetch comments for", required = true) @PathVariable long postId) {
        List<CommentDto> commentDtoList = this.commentService.getAllCommentsByPostId(postId);
        return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
    }

    // Get Single Comment REST API - /posts/{post-id}/comments/{comment-id}
    @Operation(summary = "Fetch a single comment by ID", description = "Retrieve a specific comment by its comment ID and associated post ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched the comment"),
            @ApiResponse(responseCode = "404", description = "Comment or post not found")
    })
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> fetchCommentByPostIdAndCommentId(
            @Parameter(description = "ID of the post", required = true) @PathVariable long postId,
            @Parameter(description = "ID of the comment to fetch", required = true) @PathVariable long commentId) {
        CommentDto commentDto = this.commentService.getCommentByPostIdAndCommentId(postId, commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    // Update Comment REST API - /posts/{post-id}/comments/{comment-id}
    @Operation(summary = "Update an existing comment", description = "Update a specific comment by providing the post ID, comment ID, and the new comment details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the comment"),
            @ApiResponse(responseCode = "400", description = "Invalid comment data"),
            @ApiResponse(responseCode = "404", description = "Comment or post not found")
    })
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateCommentByPostIdAndCommentId(
            @Parameter(description = "ID of the post", required = true) @PathVariable long postId,
            @Parameter(description = "ID of the comment to update", required = true) @PathVariable long commentId,
            @Parameter(description = "Updated comment details", required = true) @RequestBody CommentDto commentDto) {
        CommentDto updatedCommentDto = this.commentService.updateCommentByPostIdAndCommentId(postId, commentId, commentDto);
        return new ResponseEntity<>(updatedCommentDto, HttpStatus.OK);
    }

    // Delete Comment REST API - /posts/{post-id}/comments/{comment-id}
    @Operation(summary = "Delete a comment", description = "Delete a specific comment from a post using the post ID and comment ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted the comment"),
            @ApiResponse(responseCode = "404", description = "Comment or post not found")
    })
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentByPostIdAndCommentId(
            @Parameter(description = "ID of the post", required = true) @PathVariable long postId,
            @Parameter(description = "ID of the comment to delete", required = true) @PathVariable long commentId) {
        String message = this.commentService.deleteCommentByPostIdAndCommentId(postId, commentId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // Delete All Comments for Post REST API - /posts/{post-id}/comments
    @Operation(summary = "Delete all comments for a post", description = "Delete all comments associated with a specific post by providing the post ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted all comments for the post"),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })
    @DeleteMapping("/posts/{postId}/comments")
    public ResponseEntity<String> deleteAllCommentsByPostId(@Parameter(description = "ID of the post to delete all comments from", required = true) @PathVariable long postId) {
        String message = this.commentService.deleteAllCommentsOfPostsFromPostId(postId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}

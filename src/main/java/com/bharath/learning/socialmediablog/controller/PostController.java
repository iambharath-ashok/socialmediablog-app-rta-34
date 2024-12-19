package com.bharath.learning.socialmediablog.controller;

import com.bharath.learning.socialmediablog.dto.PostDto;
import com.bharath.learning.socialmediablog.service.PostService;
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
@RequestMapping("/v1/api/posts")
@Tag(name = "Posts API", description = "API for managing posts. Allows users to fetch, create, update, and delete posts.")
public class PostController {

    @Autowired
    private PostService postService;

    // Get all Posts
    @Operation(summary = "Fetch all posts", description = "Retrieve a list of all posts.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched all posts"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<PostDto> fetchAllPosts() {
        return this.postService.getAllPosts();
    }

    // Get Post by ID
    @Operation(summary = "Fetch a post by ID", description = "Retrieve a specific post by its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched the post"),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })
    @GetMapping("/{postId}")
    public PostDto fetchPostById(
            @Parameter(description = "ID of the post to be fetched", required = true)
            @PathVariable long postId) {
        return this.postService.getPostById(postId);
    }

    // Create Post
    @Operation(summary = "Create a new post", description = "Create a new post with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the post"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public PostDto savePost(
            @Parameter(description = "Post details to create a new post", required = true)
            @RequestBody PostDto postDto) {
        return this.postService.createPost(postDto);
    }

    // Update Post
    @Operation(summary = "Update an existing post", description = "Update an existing post by providing the post ID and new details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the post"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })
    @PutMapping("/{postId}")
    public PostDto updatePost(
            @Parameter(description = "Updated post details", required = true)
            @RequestBody PostDto postDtoToUpdated,
            @Parameter(description = "ID of the post to be updated", required = true)
            @PathVariable long postId) {
        return this.postService.updatePost(postDtoToUpdated, postId);
    }

    // Delete Post
    @Operation(summary = "Delete a post by ID", description = "Delete a specific post using its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted the post"),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(
            @Parameter(description = "ID of the post to be deleted", required = true)
            @PathVariable long postId) {
        boolean isDeleted = this.postService.deletePostById(postId);
        if (isDeleted) {
            return ResponseEntity.ok("Post " + postId + " deleted successfully");
        } else {
            return new ResponseEntity<>("Error while deleting Post " + postId, HttpStatus.NOT_FOUND);
        }
    }
}

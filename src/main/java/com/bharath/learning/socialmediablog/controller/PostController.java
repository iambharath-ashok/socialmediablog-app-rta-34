package com.bharath.learning.socialmediablog.controller;

import com.bharath.learning.socialmediablog.dto.PostDto;
import com.bharath.learning.socialmediablog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/posts")
public class PostController {

    @Autowired
    private PostService postService;


    //Get all Posts

    //v1/api/posts
    @GetMapping
    public List<PostDto> fetchAllPosts() {
      return  this.postService.getAllPosts();
    }

    // Get Posts By Id
    // /v1/api/posts/{postId}
    @GetMapping("/{postId}")
    public PostDto fetchPostById(@PathVariable long postId) {
      return  this.postService.getPostById(postId);
    }

    // Create post
    // /v1/api/posts
    @PostMapping
    public PostDto savePost(@RequestBody PostDto postDto) {
       return this.postService.createPost(postDto);
    }

    // Update Post

    // /v1/api/posts/{postId}

    @PutMapping("/{postId}")
    public PostDto updatePost(@RequestBody PostDto postDtoToUpdated, @PathVariable long postId) {
        return this.postService.updatePost(postDtoToUpdated, postId);
    }

    // Delete Post

    // /v1/api/posts/{postId}
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable long postId) {
       boolean isDeleted =  this.postService.deletePostById(postId);
       if(isDeleted) {
           return ResponseEntity.ok("Post " + postId + " delete successfull");
       } else  {
           return new ResponseEntity<>("Erro while deleting Post " + postId, HttpStatus.NOT_FOUND);
       }
    }

}

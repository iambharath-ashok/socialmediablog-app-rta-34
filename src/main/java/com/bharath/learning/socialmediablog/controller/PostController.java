package com.bharath.learning.socialmediablog.controller;

import com.bharath.learning.socialmediablog.dto.PostDto;
import com.bharath.learning.socialmediablog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
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
    // /v1/api/post
    @PostMapping
    public PostDto savePost(@RequestBody PostDto postDto) {
       return this.postService.createPost(postDto);
    }

    // Update Post
    // Delete Post

}

package com.bharath.learning.socialmediablog.service;

import com.bharath.learning.socialmediablog.dto.PostDto;
import java.util.List;

public interface PostService {

    //Get all the Posts
    List<PostDto> getAllPosts();

    // Get Post By Id
    PostDto getPostById(long id);

    // Create Post
    PostDto createPost(PostDto postDtoToBeCreated);

    // Update Post
    PostDto updatePost(PostDto postDto, long postIdToBeUpdated);

    //delete Post
    boolean deletePostById(long postIdToBeDeleted);
}

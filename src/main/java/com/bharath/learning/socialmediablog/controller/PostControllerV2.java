package com.bharath.learning.socialmediablog.controller;


import com.bharath.learning.socialmediablog.dto.PostDto;
import com.bharath.learning.socialmediablog.payload.PostResponse;
import com.bharath.learning.socialmediablog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v2/api/posts")
public class PostControllerV2 {

    @Autowired
    private PostService postService;

    @GetMapping
    public PostResponse fetchAllPosts(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                      @RequestParam(value = "pageSize", defaultValue = "0", required = false) int pageSize) {
        return  this.postService.getAllPosts(pageNo, pageSize);
    }

}

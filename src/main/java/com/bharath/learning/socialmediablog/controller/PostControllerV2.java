package com.bharath.learning.socialmediablog.controller;

import com.bharath.learning.socialmediablog.dto.PostDto;
import com.bharath.learning.socialmediablog.payload.PostResponse;
import com.bharath.learning.socialmediablog.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/api/posts")
@Tag(name = "Posts API v2", description = "API for fetching posts with pagination and sorting options.")
public class PostControllerV2 {

    @Autowired
    private PostService postService;

    @Operation(summary = "Fetch all posts with pagination and sorting", description = "Retrieve a paginated list of posts, with options to sort and filter the results.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched posts with pagination"),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public PostResponse fetchAllPosts(
            @Parameter(description = "Page number for pagination (default is 0)", required = false)
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,

            @Parameter(description = "Number of posts per page (default is 0, which means no limit)", required = false)
            @RequestParam(value = "pageSize", defaultValue = "0", required = false) int pageSize,

            @Parameter(description = "Field by which posts should be sorted (default is 'id')", required = false)
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,

            @Parameter(description = "Direction of sorting: ASC or DESC (default is ASC)", required = false)
            @RequestParam(value = "sortDirection", defaultValue = "ASC", required = false) String sortDirection) {

        return this.postService.getAllPosts(pageNo, pageSize, sortBy, sortDirection);
    }
}

package com.bharath.learning.socialmediablog.payload;

import com.bharath.learning.socialmediablog.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {

    // List of Post DTO
    // page number
    // page size
    // total elements
    // total pages
    // is last page
    private List<PostDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean isLastPage;


}

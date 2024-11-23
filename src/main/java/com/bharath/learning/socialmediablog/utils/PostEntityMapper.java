package com.bharath.learning.socialmediablog.utils;

import com.bharath.learning.socialmediablog.dto.PostDto;
import com.bharath.learning.socialmediablog.entity.PostEntity;
import org.springframework.stereotype.Component;

@Component
public class PostEntityMapper {


    //map or convert post entity to post dto
    public PostDto mapPostEntityToPostDto(final PostEntity postEntity) {
        PostDto postDto  = new PostDto();
        postDto.setId(postEntity.getId());
        postDto.setTitle(postEntity.getTitle());
        postDto.setDescription(postEntity.getDescription());
        postDto.setContent(postEntity.getContent());
        return postDto;
    }

    // map or convert post dto to post entity
    public PostEntity mapPostDtoToPostEntity(final PostDto postDto) {
        PostEntity postEntity  = new PostEntity();
        postEntity.setTitle(postDto.getTitle());
        postEntity.setDescription(postDto.getDescription());
        postEntity.setContent(postDto.getContent());
        return postEntity;
    }


}

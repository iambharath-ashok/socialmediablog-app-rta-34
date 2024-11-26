package com.bharath.learning.socialmediablog.service.impl;

import com.bharath.learning.socialmediablog.dto.PostDto;
import com.bharath.learning.socialmediablog.entity.PostEntity;
import com.bharath.learning.socialmediablog.exceptions.ResourceNotFoundException;
import com.bharath.learning.socialmediablog.payload.PostResponse;
import com.bharath.learning.socialmediablog.repository.PostRepository;
import com.bharath.learning.socialmediablog.service.PostService;
import com.bharath.learning.socialmediablog.utils.PostEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostServiceImpl.class);

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostEntityMapper postEntityMapper;

    @Override
    public List<PostDto> getAllPosts() {
        List<PostEntity>  postEntities = postRepository.findAll();

        if(postEntities != null) {
            return postEntities.stream()
                    .map(postEntity -> postEntityMapper.mapPostEntityToPostDto(postEntity))
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<PostEntity> postEntityList = postRepository.findAll(pageable);

        if(postEntityList != null) {

         List<PostDto> postDtoList = postEntityList.stream().map(postEntity -> this.postEntityMapper
                 .mapPostEntityToPostDto(postEntity))
                 .collect(Collectors.toList());

            PostResponse postResponse = PostResponse.builder()
                 .content(postDtoList)
                 .pageNo(postEntityList.getNumber())
                 .pageSize(postEntityList.getSize())
                 .totalElements(postEntityList.getTotalElements())
                 .totalPages(postEntityList.getTotalPages())
                 .isLastPage(postEntityList.isLast())
                 .build();

            return postResponse;
        }
        return null;
    }

    @Override
    public PostDto getPostById(long id) {
       Optional<PostEntity> optionalEntity =  this.postRepository.findById(id);
        return optionalEntity.map(postEntity -> postEntityMapper.mapPostEntityToPostDto(postEntity)).orElseThrow(() -> new RuntimeException("Post not found by id: "+ id));
    }

    @Override
    public PostDto createPost(PostDto postDtoToBeCreated) {
       PostEntity entityToSave = this.postEntityMapper.mapPostDtoToPostEntity(postDtoToBeCreated);
        return this.postEntityMapper.mapPostEntityToPostDto(this.postRepository.save(entityToSave));
    }

    @Override
    public PostDto updatePost(PostDto postDto, long postIdToBeUpdated) {


      PostEntity postEntityToBeUpdated =   this.postRepository.findById(postIdToBeUpdated).orElseThrow(() -> new RuntimeException("Post not found by id: "+ postIdToBeUpdated));

      postEntityToBeUpdated.setContent(postDto.getContent());
      postEntityToBeUpdated.setDescription(postDto.getDescription());
      postEntityToBeUpdated.setTitle(postDto.getTitle());

      PostEntity postEntityUpdated = this.postRepository.save(postEntityToBeUpdated);
      return this.postEntityMapper.mapPostEntityToPostDto(postEntityUpdated);
    }

    @Override
    public boolean deletePostById(long postIdToBeDeleted) {
        try {
           PostEntity postEntity = this.postRepository.findById(postIdToBeDeleted).orElseThrow(() -> new ResourceNotFoundException("Post not found by id: "+ postIdToBeDeleted));
            this.postRepository.delete(postEntity);
        } catch (Exception e) {
            LOGGER.error("Exception while deleting the Posts by Id: {}", postIdToBeDeleted);
            return false;
        }
        return true;
    }
}

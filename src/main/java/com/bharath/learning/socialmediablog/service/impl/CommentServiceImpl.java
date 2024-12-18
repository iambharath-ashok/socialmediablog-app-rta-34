package com.bharath.learning.socialmediablog.service.impl;

import com.bharath.learning.socialmediablog.dto.CommentDto;
import com.bharath.learning.socialmediablog.entity.CommentEntity;
import com.bharath.learning.socialmediablog.entity.PostEntity;
import com.bharath.learning.socialmediablog.exceptions.ResourceNotFoundException;
import com.bharath.learning.socialmediablog.repository.CommentRepository;
import com.bharath.learning.socialmediablog.repository.PostRepository;
import com.bharath.learning.socialmediablog.service.CommentService;
import com.bharath.learning.socialmediablog.utils.CommentEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentEntityMapper commentEntityMapper;




    @Override
    public List<CommentDto> getAllCommentsByPostId(long postId) {
        List<CommentEntity> commentEntitiesList = this.commentRepository.findByPostId(postId);
        if(commentEntitiesList != null && !commentEntitiesList.isEmpty()) {
            return commentEntitiesList.stream().map(commentEntity -> this.commentEntityMapper.convertEntityToDto(commentEntity)).toList();
        }
        return null;
    }

    @Override
    public CommentDto getCommentByPostIdAndCommentId(long postId, long commentId) {

        // validate or fetch post entity details from post table of DB
        PostEntity postEntity = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post id not found ::"+ postId));

        // fetch comment by comment id
        CommentEntity commentEntity = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment id not found ::"+ commentId));

        // validate the particular comment belongs to that post
        if(commentEntity != null && postEntity != null) {
            if(!commentEntity.getPostEntity().getId().equals(postEntity.getId())) {
                throw new RuntimeException("Bad Request :: Comment Not Found");
            } else {
                return this.commentEntityMapper.convertEntityToDto(commentEntity);
            }
        }

        throw new RuntimeException("Bad Request");
    }

    @Override
    public CommentDto createCommentForPost(long postId, CommentDto commentDto) {

        //Convert CommentDto to CommentEntity
        CommentEntity commentEntity = this.commentEntityMapper.convertDtoToEntity(commentDto);

        // Fetch Post Entity from Post Table using postId
        PostEntity postEntity = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post Id Not Found:: "+ postId));

        //Attach or Set Comment to Particular or associated Post Entity
        commentEntity.setPostEntity(postEntity);

        // Save Comment Entity to DB
        CommentEntity newlySavedCommentEntity = this.commentRepository.save(commentEntity);

        //Map Comment Entity to Comment DTO and return newly create Comment DTO
        return this.commentEntityMapper.convertEntityToDto(newlySavedCommentEntity);
    }

    @Override
    public CommentDto updateCommentByPostIdAndCommentId(long postId, long commentId, CommentDto commentDto) {

        //Fetch Post Entity using Post Repository from Post id
        PostEntity postEntity = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post Id Not Found:: "+ postId));

        //Fetch Comment Entity using Comment Repository from Comment id
        CommentEntity commentEntity = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment id not found ::"+ commentId));

        // Validate comment belong to that particular Post
        if(commentEntity != null && postEntity != null) {
            if(!commentEntity.getPostEntity().getId().equals(postEntity.getId())) {
                throw new RuntimeException("Bad Request :: Comment Not Found");
            }
        }

        // if valid  then Update Old Comment Details with new Comment Dto
        if (commentEntity != null && commentDto != null) {
            commentEntity.setEmail(commentDto.getEmail());
            commentEntity.setUserName(commentDto.getUserName());
            commentEntity.setBody(commentDto.getBody());
        }

        // Save updated Comment Entity to DB
        CommentEntity newlySavedCommentEntity = this.commentRepository.save(commentEntity);


        return this.commentEntityMapper.convertEntityToDto(newlySavedCommentEntity);
    }

    @Override
    public String deleteCommentByPostIdAndCommentId(long postId, long commentId) {
        //Fetch Post Entity using Post Repository from Post id
        PostEntity postEntity = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post Id Not Found:: "+ postId));

        //Fetch Comment Entity using Comment Repository from Comment id
        CommentEntity commentEntity = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment id not found ::"+ commentId));

        // Validate comment belong to that particular Post
        if(commentEntity != null && postEntity != null) {
            if(!commentEntity.getPostEntity().getId().equals(postEntity.getId())) {
                throw new RuntimeException("Bad Request :: Comment Not Found");
            }
        }

        this.commentRepository.delete(commentEntity);
        return "Successfully Deleted Comment : "+ commentId;
    }

    @Override
    public String deleteAllCommentsOfPostsFromPostId(long postId) {
      this.commentRepository.deleteByPostId(postId);
      return "Successfully Deleted Comments For Post : "+ postId;
    }
}

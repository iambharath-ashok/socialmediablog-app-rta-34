package com.bharath.learning.socialmediablog.utils;

import com.bharath.learning.socialmediablog.dto.CommentDto;
import com.bharath.learning.socialmediablog.entity.CommentEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentEntityMapper {

       @Autowired
       private ModelMapper modelMapper;


       public CommentDto convertEntityToDto(CommentEntity commentEntity) {
           if(commentEntity != null) {
//               CommentDto commentDto = new CommentDto();
//               commentDto.setId(commentEntity.getId());
//               commentDto.setUserName(commentEntity.getUserName());
//               commentDto.setEmail(commentEntity.getEmail());
//               commentDto.setBody(commentEntity.getBody());
               return this.modelMapper.map(commentEntity, CommentDto.class);
           }
           return null;
       }

    public CommentEntity convertDtoToEntity(CommentDto commentDto) {
        if(commentDto != null) {
//            CommentEntity commentEntity = new CommentEntity();
//            commentEntity.setUserName(commentDto.getUserName());
//            commentEntity.setEmail(commentDto.getEmail());
//            commentEntity.setBody(commentDto.getBody());
            return this.modelMapper.map(commentDto, CommentEntity.class);
        }
        return null;
    }


}

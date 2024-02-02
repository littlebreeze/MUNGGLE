package com.munggle.comment.mapper;

import com.munggle.comment.dto.CommentCreateDto;
import com.munggle.comment.dto.CommentDetailDto;
import com.munggle.domain.model.entity.Comment;
import com.munggle.domain.model.entity.Post;
import com.munggle.domain.model.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public static Comment toEntity(CommentCreateDto commentCreateDto){
        return Comment.builder()
                .post(Post.builder()
                        .id(commentCreateDto.getPostId()).build())
                .user(User.builder()
                        .id(commentCreateDto.getUserId()).build())
                .contents(commentCreateDto.getContents())
                .build();
    }

    public static CommentDetailDto toDto(Comment comment){
        return CommentDetailDto.builder()
                .commentId(comment.getId())
                .userId(comment.getUser().getId())
                .postId(comment.getPost().getId())
                .contents(comment.getContents())
                //.likeCnt(comment.getLikeCnt())
                .isDeleted(comment.getIsDeleted())
                .build();
    }
}

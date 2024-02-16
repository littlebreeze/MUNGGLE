package com.munggle.comment.mapper;

import com.munggle.comment.dto.CommentCreateDto;
import com.munggle.comment.dto.CommentDetailDto;
import com.munggle.domain.model.entity.Comment;
import com.munggle.domain.model.entity.Post;
import com.munggle.domain.model.entity.User;
import com.munggle.user.mapper.UserMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public static Comment toEntity(String content, Post post, User user){
        return Comment.builder()
                .post(post)
                .user(user)
                .contents(content)
                .build();
    }

    public static CommentDetailDto toDto(Comment comment, Boolean haveLiked){
        return CommentDetailDto.builder()
                .commentId(comment.getId())
                .postId(comment.getPost().getId())
                .contents(comment.getContents())
                //.likeCnt(comment.getLikeCnt())
                .isDeleted(comment.getIsDeleted())
                .user(UserMapper.toUserProfileDto(comment.getUser()))
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .haveLiked(haveLiked)
                .build();
    }
}

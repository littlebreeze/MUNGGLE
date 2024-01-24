package com.munggle.post.mapper;

import com.munggle.domain.model.entity.Post;
import com.munggle.post.dto.PostCreateDto;
import com.munggle.post.dto.PostDetailResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostMapper {

    public static Post toEntity(PostCreateDto postCreateDto) {
        return Post.builder()
                .postTitle(postCreateDto.getPostTitle())
                .postContent(postCreateDto.getPostContent())
                .user(postCreateDto.getUser())
                .isPrivate(postCreateDto.getIsPrivate())
                .build();
    }

    public static PostDetailResponseDto toPostDetailResponseDto(Post detailPost, String nickname, Boolean isMine) {
        return PostDetailResponseDto.builder()
                .postTitle(detailPost.getPostTitle())
                .postContent(detailPost.getPostContent())
                .nickname(nickname)
                .likeCnt(detailPost.getLikeCnt())
                .isMine(isMine)
                .createdAt(detailPost.getCreatedAt())
                .updatedAt(detailPost.getUpdatedAt())
                .isUpdated(detailPost.getCreatedAt().equals(detailPost.getUpdatedAt()))
                .build();
    }
}

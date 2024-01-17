package com.munggle.post.mapper;

import com.munggle.domain.model.entity.Post;
import com.munggle.post.dto.PostCreateDto;
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
                .userId(postCreateDto.getUserId())
                .isPrivate(postCreateDto.getIsPrivate())
                .build();
    }
}

package com.munggle.post.mapper;

import com.munggle.domain.model.entity.*;
import com.munggle.image.dto.FileInfoDto;
import com.munggle.post.dto.PostCreateDto;
import com.munggle.post.dto.PostDetailResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostMapper {

    public static Post toEntity(PostCreateDto postCreateDto) {
        return Post.builder()
                .postTitle(postCreateDto.getPostTitle())
                .postContent(postCreateDto.getPostContent())
                .isDeleted(false)
                .isPrivate(postCreateDto.getIsPrivate())
                .build();
    }

    public static PostDetailResponseDto toPostDetailResponseDto(Post detailPost, String nickname, Boolean isMine, List<String> imageUrls, List<String> hashtags) {
        return PostDetailResponseDto.builder()
                .postTitle(detailPost.getPostTitle())
                .postContent(detailPost.getPostContent())
                .images(imageUrls)
                .hashtags(hashtags)
                .nickname(nickname)
                .likeCnt(detailPost.getLikeCnt())
                .isMine(isMine)
                .createdAt(detailPost.getCreatedAt())
                .updatedAt(detailPost.getUpdatedAt())
                .isUpdated(detailPost.getCreatedAt().equals(detailPost.getUpdatedAt()))
                .build();
    }

    public static PostImage toPostImageEntity(FileInfoDto file, Post post) {
        return PostImage.builder()
                .imageName(file.getFileName())
                .imageURL(file.getFileURL())
                .post(post)
                .build();
    }

    public static Tag toTagEntity(String tagNm) {
        return Tag.builder()
                .tagNm(tagNm)
                .build();
    }

    public static PostTagId toPostTagIdEntity(Long postId, Long tagId) {
        return PostTagId.builder()
                .postId(postId)
                .tagId(tagId)
                .build();
    }

    public static PostTag toPostTagEntity(PostTagId postTagId, Post post, Tag tag) {
        return PostTag.builder()
                .postTagId(postTagId)
                .post(post)
                .tag(tag)
                .build();
    }
}

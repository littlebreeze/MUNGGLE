package com.munggle.post.mapper;

import com.munggle.domain.model.entity.*;
import com.munggle.image.dto.FileInfoDto;
import com.munggle.post.dto.request.PostCreateDto;
import com.munggle.post.dto.response.PostDetailDto;
import com.munggle.post.dto.response.PostListDto;
import com.munggle.post.dto.response.UserPostListDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public static PostDetailDto toPostDetailResponseDto(Post detailPost, String nickname, Boolean isMine, List<String> imageUrls, List<String> hashtags) {
        return PostDetailDto.builder()
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
                .isDeleted(false)
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

    public static PostListDto toPostListDto(Post post) {
        List<String> imageUrls = post.getPostImageList().stream()
                .map(PostImage::getImageURL)
                .collect(Collectors.toList());

        List<String> hashtags = post.getPostTagList().stream()
                .filter(postTag -> !postTag.getIsDeleted())
                .map(PostTag::getTag)
                .map(Tag::getTagNm)
                .collect(Collectors.toList());

        return PostListDto.builder()
                .postId(post.getId())
                .postTitle(post.getPostTitle())
                .imageURLs(imageUrls)
                .hashtags(hashtags)
                .userId(post.getUser().getId())
                .nickname(post.getUser().getNickname())
                .likeCnt(post.getLikeCnt())
                .createdAt(post.getCreatedAt())
                .build();
    }

    public static UserPostListDto toUserPagePostList(Post post) {
        return UserPostListDto.builder()
                .postId(post.getId())
                .postTitle(post.getPostTitle())
                .imageURL(post.getPostImageList().get(0).getImageURL())
                .build();
    }

    public static PostLikeId toPostLikeIdEntity(Long userId, Long postId) {
        return PostLikeId.builder()
                .userId(userId)
                .postId(postId)
                .build();
    }

    public static PostLike toPostLikeEntity(PostLikeId postLikeId, User user, Post post) {
        return PostLike.builder()
                .postLikeId(postLikeId)
                .user(user)
                .post(post)
                .isDeleted(false)
                .build();
    }
}

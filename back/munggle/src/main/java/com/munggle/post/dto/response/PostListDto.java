package com.munggle.post.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PostListDto {

    private Long postId;

    private String postTitle;

    private List<String> imageURLs;

    private List<String> hashtags;

    private Long userId;

    private String profileImage;

    private String nickname;

    private Integer likeCnt;

    private Boolean isLiked;

    private Boolean isFollowed;

    private LocalDateTime createdAt;

}

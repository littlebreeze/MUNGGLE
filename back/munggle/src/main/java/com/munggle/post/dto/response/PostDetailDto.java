package com.munggle.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class PostDetailDto {

    private String postTitle;

    private String postContent;

    private List<String> images;

    private List<String> hashtags;

    private Long userId;

    private String profileImage;

    private String nickname;

    private Integer likeCnt;

    private Boolean isLiked;

    private Boolean isScraped;

    private Boolean isMine;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean isUpdated;

}

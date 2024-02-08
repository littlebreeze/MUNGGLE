package com.munggle.search.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class SearchPostListDto {
    private Long postId;

    private String postTitle;

    private List<String> imageURLs;

    private Long userId;

    private String profileImage;

    private String nickname;

    private Integer likeCnt;

    private Boolean isLiked;

    private Boolean isFollowed;

    private LocalDateTime createdAt;

}

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

    private String nickname;

    private Integer likeCnt;

    private LocalDateTime createdAt;

    // 추후 유저 프로필 이미지와 좋아요 유무 추가 예정

}

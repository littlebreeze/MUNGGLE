package com.munggle.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@Data
@AllArgsConstructor
public class PostDetailResponseDto {

    private String postTitle;

    private String postContent;

    private List<String> images;

    private String nickname;

    private Integer likeCnt;

    private Boolean isMine;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean isUpdated;


}

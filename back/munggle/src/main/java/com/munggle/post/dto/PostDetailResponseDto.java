package com.munggle.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@Builder
@Data
@AllArgsConstructor
public class PostDetailResponseDto {

    String postTitle;
    String postContent;
    String nickname;
    Integer likeCnt;
    Boolean isMine;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    Boolean isUpdated;


}

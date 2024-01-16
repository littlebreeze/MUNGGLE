package com.munggle.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostCreateDto {

    private long postNo;

    private long userId;

    @Size(max = 100)
    @NotBlank
    private String postTitle;

    @Size(max = 500)
    private String postContent;

    private int likeCnt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean isDeleted;

    private boolean isPrivate;

    public PostCreateDto(long postNo, long userId, String postTitle,
                   String postContent, int likeCnt, LocalDateTime createdAt,
                   boolean isDeleted, boolean isPrivate) {
        this.postNo = postNo;
        this.userId = userId;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.likeCnt = likeCnt;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
        this.isPrivate = isPrivate;
    }
}

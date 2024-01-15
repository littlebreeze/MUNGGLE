package com.munggle.post.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostDto {
    private long postNo;
    private long userNo;
    private String postTitle;
    private String postContent;
    private int likeCnt;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;
    private boolean isDeleted;
    private boolean isPrivate;

    public PostDto(long postNo, long userNo, String postTitle,
                   String postContent, int likeCnt, LocalDateTime registerDate,
                   boolean isDeleted, boolean isPrivate) {
        this.postNo = postNo;
        this.userNo = userNo;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.likeCnt = likeCnt;
        this.registerDate = registerDate;
        this.isDeleted = isDeleted;
        this.isPrivate = isPrivate;
    }
}

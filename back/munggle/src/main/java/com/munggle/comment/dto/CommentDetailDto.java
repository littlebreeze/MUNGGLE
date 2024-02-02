package com.munggle.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDetailDto {

    private Long commentId;
    private Long userId;
    private Long postId;
    private String contents;
    private Integer likeCnt;
    private Boolean isDeleted;

    private String userImage;

}

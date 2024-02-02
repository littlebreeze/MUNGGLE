package com.munggle.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateDto {

    private Long commentId;
    private Long userId;
    private Long postId;

    @NotBlank
    //@Size(max = )
    private String contents;

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }
}

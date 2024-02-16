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
public class CommentUpdateDto {

    private Long commentId;

    @NotBlank
    //@Size(max = )
    private String contents;

    public void setCommentId(Long commentId){
        this.commentId = commentId;
    }
}

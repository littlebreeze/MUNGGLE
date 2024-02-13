package com.munggle.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageCommentDto {

    @Builder.Default
    private List<CommentDetailDto> comments = new ArrayList<>();

    private Boolean last;

}

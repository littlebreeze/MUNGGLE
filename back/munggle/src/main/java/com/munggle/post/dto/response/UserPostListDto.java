package com.munggle.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserPostListDto {

    private Long postId;

    private String postTitle;

    private String imageURL;
}

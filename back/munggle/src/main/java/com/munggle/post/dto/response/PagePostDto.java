package com.munggle.post.dto.response;

import com.munggle.search.dto.SearchPostListDto;
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
public class PagePostDto {

    @Builder.Default
    private List<PostListDto> posts = new ArrayList<>();

    private Boolean last;

}

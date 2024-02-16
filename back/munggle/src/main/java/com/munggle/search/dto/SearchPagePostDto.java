package com.munggle.search.dto;

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
public class SearchPagePostDto {

    @Builder.Default
    private List<SearchPostListDto> posts = new ArrayList<>();

    private Boolean last;
}

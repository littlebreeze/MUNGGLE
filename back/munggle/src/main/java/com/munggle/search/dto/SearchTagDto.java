package com.munggle.search.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class SearchTagDto {

    private Long tagId;

    private String tagNm;

}

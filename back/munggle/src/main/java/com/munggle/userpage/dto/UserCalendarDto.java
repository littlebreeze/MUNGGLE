package com.munggle.userpage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserCalendarDto {

    private Integer day;

    private Long postId;

    private String imageURL;
}

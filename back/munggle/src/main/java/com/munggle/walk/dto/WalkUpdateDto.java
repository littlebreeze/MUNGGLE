package com.munggle.walk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WalkUpdateDto {
    private Long walkId;
    private Integer duration;
    private Integer distance;
    private Float rating;
    private String description;
}

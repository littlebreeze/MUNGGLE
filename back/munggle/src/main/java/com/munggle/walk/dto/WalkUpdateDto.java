package com.munggle.walk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WalkUpdateDto {
    private Long walkId;
    private String walkName;
    private Float rating;
    private String description;
}

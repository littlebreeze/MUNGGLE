package com.munggle.walk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalkUpdateDto {
    private Long walkId;
    private String walkName;
    private Float rating;
    private String description;

    public void setWalkId(Long walkId){
        this.walkId = walkId;
    }
}

package com.munggle.walk.dto;

import com.munggle.user.dto.UserProfileDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalkDto {

    private Long walkId;
    private String walkName;
    private Long dogId;
    private String description;
    private Integer duration;
    private Integer distance;
    private Float rating;
    private Boolean isDeleted;
    private Boolean isPrivated;
    private String image;

    private UserProfileDto user;

    private List<LocationDetailDto> location;

}

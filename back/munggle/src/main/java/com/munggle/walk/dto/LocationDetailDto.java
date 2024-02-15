package com.munggle.walk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LocationDetailDto {

    private Float lat;
    private Float lng;
    private Long orderNo;
}

package com.munggle.walk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalkCalendarDto {

    List<WalkDto> walkList;
    Integer totalCnt;
    Float totalDistance;
    Integer totalDuration;
}

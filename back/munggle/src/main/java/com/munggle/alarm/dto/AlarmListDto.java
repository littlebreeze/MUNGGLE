package com.munggle.alarm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlarmListDto {

    private Long userId;

    private List<AlarmDetailDto> alarms;

}

package com.munggle.alarm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlarmDto {

    private Long alarmId;

    private String alarmType;

    private Long fromUserId;

    private Long target;

    private Boolean isChecked;

}

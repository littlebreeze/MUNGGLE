package com.munggle.alarm.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlarmCreateDto {

    private Long alarmId;

    private String alarmType;

    private Long fromUserId;

    private Long toUserId;

    private Long target;

}

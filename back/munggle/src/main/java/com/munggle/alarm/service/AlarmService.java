package com.munggle.alarm.service;

import com.munggle.alarm.dto.AlarmCreateDto;
import com.munggle.alarm.dto.AlarmListDto;

public interface AlarmService {

    AlarmListDto getAlarmList(Long userId);

    Integer countAlarm(Long userId);

    void InsetAlarm(AlarmCreateDto alarmCreateDto);

    void deleteAlarm(Long alarmId);

}
